// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.Rust.Rust_Type.Rust_TypePrimitive;
import com.eagle.programmar.Rust.Statements.Rust_Block_Statement;
import com.eagle.programmar.Rust.Symbols.Rust_Function_Definition;
import com.eagle.programmar.Rust.Symbols.Rust_Variable_Definition;
import com.eagle.programmar.Rust.Terminals.Rust_Comment;
import com.eagle.programmar.Rust.Terminals.Rust_Keyword;
import com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Rust_Function extends TokenSequence
		implements EagleRunnable, AbstractFunction, EagleScopeInterface, EagleTransformableFunction
{
	public @S(10) @OPT @BLANKLINE Rust_Keyword PUB = new Rust_Keyword("pub");
	public @S(20) @DOC("items/functions.html") Rust_Keyword FN = new Rust_Keyword("fn");
	public @S(30) Rust_Function_Definition id;
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @OPT @NOSPACE SeparatedList<Rust_Parameter, PunctuationComma> funcParamDefs;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	public @S(70) @OPT Rust_FunctionReturns returns;
	public @S(80) Rust_Block_Statement block;

	public static class Rust_FunctionReturns extends TokenSequence
	{
		public @S(10) Rust_Punctuation arrow = new Rust_Punctuation("->");
		public @S(20) Rust_Type returnType;
	}

	public static class Rust_Parameter extends TokenSequence
	{
		public @S(10) Rust_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) Rust_Type type;
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Rust_Syntax.IS_CASE_SENSITIVE);
	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called

		// Except the function called 'main'
		if (id.getValue().equals("main"))
		{
			interpreter.callingFunction("main", this);
			interpreter.tryToInterpret(block);
			interpreter.completedFunction("main", this);
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractType newReturnType = null;
		if (returns != null && returns.isPresent())
		{
			if (returns.returnType.getWhich() instanceof Rust_TypePrimitive)
			{
				Rust_TypePrimitive primitive = (Rust_TypePrimitive) returns.returnType.getWhich();
				newReturnType = Rust_Type.findType(generator, primitive.PRIMITIVE.toString());
			}
		}

		if (newReturnType == null)
		{
			TypeEnum metricRetType = transformer.findReturnMetric(id);
			newReturnType = generator.transformType(metricRetType, null, id);
		}

		String fnName = id.getValue();
		boolean isMain = false;
		if (fnName.equals("main"))
		{
			fnName = generator.mainName(); // Change from 'main' to 'Main' for C#
			isMain = true;
		}

		generator.addMethod(newReturnType, fnName, this);
		generator.addMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Rust function " + fnName);
		}

		if (isMain)
		{
			// Have to wait until addMethod is called
			generator.addMainArgs(); // For java and C# but not for Python or Rust
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (funcParamDefs != null && funcParamDefs.isPresent())
		{
			for (int i = 0; i < funcParamDefs.getPrimaryCount(); i++)
			{
				Rust_Parameter param = funcParamDefs.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, param);
				}

				// System.err.println("****** paramType = " + paramType + " value = " +
				// param.getValue());
				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}

		// addLocalVars(transformer, generator);

		Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, block);
		if (newStmts != null)
		{
			for (AbstractStatement newStmt : newStmts)
			{
				generator.addStatement(newStmt, block);
			}
		}

		generator.doneMethod();
	}

//	private boolean isFuncParam(String name)
//	{
//		if (funcParamDefs != null && funcParamDefs.isPresent())
//		{
//			int numParams = funcParamDefs.getPrimaryCount();
//			for (int i = 0; i < numParams; i++)
//			{
//				Rust_Parameter param = funcParamDefs.getPrimaryElement(i);
//				Rust_Variable_Definition var = param.var;
//				if (var.getValue().equalsIgnoreCase(name))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}

//	// Are there any local variables we need to declare?
//	private void addLocalVars(EagleTransformer transformer,
//			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		String scopeStr = this._currentLine + "-" + this._endLine;
//		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
//		for (AssignMetrics met : asgMetrics)
//		{
//			TypeEnum typ = met.uniqueType();
//			if (typ != TypeEnum.VOID)
//			{
//				if (!isFuncParam(met._symbolName))
//				{
//					// System.err.println("****** Found var " + met._symbolName);
//					AbstractType absType = generator.transformType(typ, null, this);
//					AbstractStatement dataStmt = generator.newDataDeclaration(StaticEnum.NONE,
//							met._symbolName, null, absType, null, this);
//					generator.addStatement(dataStmt, this);
//				}
//			}
//		}
//	}

	public void newRustFunction(Rust_Type returnType, String methodName)
	{
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();

		if (returnType != null)
		{
			this.returns = new Rust_FunctionReturns();
			this.returns.setPresent(true);
			this.returns.returnType = returnType;
		}

		this.block = new Rust_Block_Statement();
		this.block.leftBrace = new PunctuationLeftBrace();
		this.block.statements = new TokenList<Rust_Statement>();
		this.block.rightBrace = new PunctuationRightBrace();

		this.id = new Rust_Function_Definition();
		this.id.setValue(methodName);
	}

	public void addFunctionParameter(Rust_Type type, String name)
	{
		Rust_Parameter param = new Rust_Parameter();
		param.var = new Rust_Variable_Definition();
		param.var.setValue(name);
		param.colon = new PunctuationColon();
		param.type = type;
		
		if (type.getWhich() instanceof Rust_TypePrimitive)
		{
			Rust_TypePrimitive prim = (Rust_TypePrimitive) type.getWhich();
			if (prim.PRIMITIVE.getValue().equals("String"))
			{
				// &str is a read-only pointer, String is mutable
				// Don't ever want to change function parameters
				prim.PRIMITIVE.setValue("&str");
			}
		}
		
		if (funcParamDefs == null)
		{
			this.funcParamDefs = new SeparatedList<Rust_Parameter, PunctuationComma>();
			this.funcParamDefs.setPresent(true);
		}
		
		if (this.funcParamDefs.size() > 0)
		{
			this.funcParamDefs.addSecondaryElement(new PunctuationComma());
		}
		this.funcParamDefs.addPrimaryElement(param);
	}
	
	public void addComment(Rust_Comment comm)
	{
		Rust_Statement stmt = new Rust_Statement();
		stmt.setWhich(comm);
		this.block.statements._elements.add(stmt);
	}
}
