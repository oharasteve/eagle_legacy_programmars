// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Go.Go_Statement;
import com.eagle.programmar.Go.Go_Syntax;
import com.eagle.programmar.Go.Go_Type;
import com.eagle.programmar.Go.Symbols.Go_Function_Definition;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Go_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @DOC("#Function_declarations") Go_Keyword FUNC = new Go_Keyword("func");
	public @S(20) Go_Function_Definition id;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<Go_FunctionParameter, PunctuationComma> funcParamDefs;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT Go_FuncReturnType returnType;
	public @S(70) Go_Statement stmt;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	public static class Go_FunctionParameter extends TokenSequence
	{
		public @S(10) Go_Variable_Definition var;
		public @S(20) @OPT PunctuationStar star;
		public @S(30) Go_Type type;
	}

	public static class Go_FuncReturnType extends TokenChooser
	{
		public @CHOICE Go_Type XXtype;

		public @CHOICE static class Go_FuncReturnMulti extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) SeparatedList<Go_Type, PunctuationComma> types;
			public @S(30) PunctuationRightParen rightParen;
		}
	}

	private @SKIP EagleScope _scope = new EagleScope(this, Go_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Don't run it here. Wait until it is called.
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
		}
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
		}
		
		// Unless the name is 'main'
		if (id.getValue().equals("main"))
		{
			interpreter.callingFunction("main", this);
			interpreter.tryToInterpret(stmt);
			interpreter.completedFunction("main", this);
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);
		
		String fnName = id.getValue();
		boolean isMain = false;
		if (fnName.equals("main"))
		{
			fnName = generator.mainName();	// Change from 'main' to 'Main' for C#
			isMain = true;
		}

		generator.addMethod(newReturnType, fnName, this);
		generator.addMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Go function " + fnName);
		}
		
		if (isMain)
		{
			// Have to wait until addMethod is called
			generator.addMainArgs();		// For java and C# but not for Python
		}
		
		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);
		
		if (funcParamDefs != null && funcParamDefs.isPresent())
		{
			for (int i = 0; i < funcParamDefs.getPrimaryCount(); i++)
			{
				Go_FunctionParameter param = funcParamDefs.getPrimaryElement(i);
				AbstractType paramType = null;
				
				if (argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(metricArg, null, param);
				}
				
				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}

		addLocalVars(transformer, generator);
		
		Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
		if (newStmts != null)
		{
			for (AbstractStatement newStmt : newStmts)
			{
				generator.addStatement(newStmt, stmt.getWhich());
			}
		}
		
		generator.doneMethod();
	}
	
	private boolean isFuncParam(String name)
	{
		int numParams = funcParamDefs.getPrimaryCount();
		for (int i = 0; i < numParams; i++)
		{
			Go_FunctionParameter param = funcParamDefs.getPrimaryElement(i);
			if (param.var.getValue().equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}
	
	// Are there any local variables we need to declare?
	private void addLocalVars(EagleTransformer transformer, EagleGenerator generator)
	{
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typ = met.uniqueType();
			if (typ != TypeEnum.VOID)
			{
				if (! isFuncParam(met._symbolName))
				{
					// System.err.println("****** Found local var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
