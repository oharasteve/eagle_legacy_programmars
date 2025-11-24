// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Julia_Syntax;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Symbols.Julia_Function_Definition;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Julia_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @DOC("manual/functions/") Julia_Keyword FUNCTION = new Julia_Keyword("function");
	public @S(20) Julia_Function_Definition id;
	public @S(30) @OPT Julia_FunctionParams params;
	public @S(40) Julia_EOLN eoln1;
	public @S(50) TokenList<Julia_Statement> stmts;
	public @S(60) Julia_Keyword END = new Julia_Keyword("end");
	public @S(70) Julia_EOLN eoln2;

	public static class Julia_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Julia_Variable, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Julia_Syntax.IS_CASE_SENSITIVE);

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
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String fnName = id.getValue();
		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Julia function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);

		if (params != null && params.isPresent())
		{
			for (int i = 0; i < params.parameters.getPrimaryCount(); i++)
			{
				Julia_Variable paramVar = params.parameters.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(metricArg, null, paramVar);
				}

				// System.err.println("****** paramType = " + paramType + " value = " +
				// param.getValue());
				generator.addMethodParameter(paramType, paramVar.vars.first().getValue());
			}
		}

		addLocalVars(transformer, generator);

		for (Julia_Statement stmt : stmts._elements)
		{
			AbstractToken which = stmt.getWhich();

			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt);
				}
			}
		}

		generator.doneMethod();
	}

	private boolean isFuncParam(String name)
	{
		if (params != null && params.isPresent())
		{
			int numParams = params.parameters.getPrimaryCount();
			for (int i = 0; i < numParams; i++)
			{
				Julia_Variable var = params.parameters.getPrimaryElement(i);
				if (var.vars.first().getValue().equalsIgnoreCase(name))
				{
					return true;
				}
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
				if (!isFuncParam(met._symbolName))
				{
					// System.err.println("****** Found var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(false,
							met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
