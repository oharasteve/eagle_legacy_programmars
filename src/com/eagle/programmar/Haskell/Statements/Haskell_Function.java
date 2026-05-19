// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 14, 2026

package com.eagle.programmar.Haskell.Statements;

import com.eagle.programmar.Haskell.Haskell_ComplexStatement;
import com.eagle.programmar.Haskell.Haskell_Type;
import com.eagle.programmar.Haskell.Symbols.Haskell_Function_Definition;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Symbols.Haskell_Parameter_Definition;
import com.eagle.programmar.Haskell.Terminals.Haskell_EndOfLine;
import com.eagle.programmar.Haskell.Terminals.Haskell_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Haskell_Function extends TokenSequence
//		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
//				EagleTransformableFunction
{
	public @S(10) Haskell_FunctionPrototype prototype;
	public @S(20) Haskell_FunctionDefinition definition;
	
	public static class Haskell_FunctionPrototype extends TokenSequence
	{
		public @S(10) Haskell_Function_Definition id;
		public @S(20) Haskell_Punctuation colonColon = new Haskell_Punctuation("::");
		public @S(30) Haskell_Type type;
		public @S(40) @OPT TokenList<Haskell_ArrowType> types;
		public @S(50) Haskell_EndOfLine eoln;
		
		public static class Haskell_ArrowType extends TokenSequence
		{
			public @S(10) Haskell_Punctuation arrow = new Haskell_Punctuation("->");
			public @S(20) Haskell_Type type;
		}
	}

	public static class Haskell_FunctionDefinition extends TokenSequence
	{
		public @S(10) Haskell_Identifier_Reference ref;
		public @S(20) @OPT TokenList<Haskell_Parameter_Definition> params;
		public @S(30) @OPT Haskell_FunctionAssignment func;
		public @S(40) Haskell_EndOfLine eoln;
		
		public static class Haskell_FunctionAssignment extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Haskell_ComplexStatement statement;
		}
	}

//	public @SKIP CallMetrics _callMetrics = null;
//	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
//	public @SKIP ReturnMetrics _returnMetrics = null;
//
//	private @SKIP EagleScope _scope = new EagleScope(this, Haskell_Syntax.IS_CASE_SENSITIVE);
//
//	@Override
//	public EagleScope getScope()
//	{
//		return _scope;
//	}
//
//	@Override
//	public void interpret(EagleInterpreter interpreter)
//	{
//		if (_callMetrics == null)
//		{
//			_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
//		}
//		if (_argumentsMetrics == null)
//		{
//			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
//		}
//		if (_returnMetrics == null)
//		{
//			_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
//		}
//
//		// Don't do anything here.
//		// We searched for all the functions in a preliminary pass
//		// And we only evaluate when it is called
//	}
//
//	@Override
//	public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
//	{
//		TypeEnum metricRetType = transformer.findReturnMetric(id);
//		AbstractType newReturnType = generator.transformType(metricRetType, null, id);
//
//		String fnName = id.getValue();
//		generator.addMethod(newReturnType, fnName, this);
//		generator.setMethodName(fnName);
//		if (VERBOSE)
//		{
//			System.out.println("** Found Haskell function " + fnName);
//		}
//
//		// Search metrics for arg types -- might not be any
//		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);
//
//		if (params != null && params.isPresent())
//		{
//			for (int i = 0; i < params.parameters.getPrimaryCount(); i++)
//			{
//				Haskell_Variable paramVar = params.parameters.getPrimaryElement(i);
//				AbstractType paramType = null;
//
//				if (argTypes != null && i < argTypes.size())
//				{
//					TypeEnum metricArg = argTypes.get(i);
//					paramType = generator.transformType(metricArg, null, paramVar);
//				}
//
//				// System.err.println("****** paramType = " + paramType + " value = " +
//				// param.getValue());
//				generator.addMethodParameter(paramType, paramVar.vars.first().getValue());
//			}
//		}
//
//		addLocalVars(transformer, generator);
//
//		for (Haskell_Statement stmt : stmts._elements)
//		{
//			AbstractToken which = stmt.getWhich();
//
//			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
//			if (newStmts != null)
//			{
//				for (AbstractStatement newStmt : newStmts)
//				{
//					generator.addStatement(newStmt, stmt);
//				}
//			}
//		}
//
//		generator.doneMethod();
//	}
//
//	private boolean isFuncParam(String name)
//	{
//		if (params != null && params.isPresent())
//		{
//			int numParams = params.parameters.getPrimaryCount();
//			for (int i = 0; i < numParams; i++)
//			{
//				Haskell_Variable var = params.parameters.getPrimaryElement(i);
//				if (var.vars.first().getValue().equalsIgnoreCase(name))
//				{
//					return true;
//				}
//			}
//		}
//		return false;
//	}
//
//	// Are there any local variables we need to declare?
//	private void addLocalVars(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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
//					AbstractStatement dataStmt = generator.newDataDeclaration(false,
//							met._symbolName, null, absType, null, this);
//					generator.addStatement(dataStmt, this);
//				}
//			}
//		}
//	}
}
