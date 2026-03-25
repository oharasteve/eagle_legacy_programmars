// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Eaglish_Syntax;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Function_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Eaglish_Function extends TokenSequence
		implements EagleRunnable, AbstractFunction, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) Eaglish_Keyword FUNCTION = new Eaglish_Keyword("FUNCTION");
	public @S(20) Eaglish_Function_Definition id;
	public @S(30) Eaglish_EndOfLine eoln1;

	public @S(40) @OPT TokenList<Eaglish_Parameter_Statement> parameterStatements;
	public @S(50) @OPT Eaglish_Returns_Statement returnsStatement;
	public @S(60) @OPT TokenList<Eaglish_Statement> statements;

	public @S(70) Eaglish_Keyword END_FUNCTION = new Eaglish_Keyword("END_FUNCTION");
	public @S(80) Eaglish_EndOfLine eoln2;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Eaglish_Syntax.IS_CASE_SENSITIVE);

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
	public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String fnName = id.getValue();
		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Eaglish function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (parameterStatements != null && parameterStatements.isPresent())
		{
			int i = 0;
			for (Eaglish_Parameter_Statement param : parameterStatements._elements)
			{
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, param);
				}

				generator.addMethodParameter(paramType, param.param.getValue());
				i++;
			}
		}

		// addLocalVars(transformer, generator);

		for (Eaglish_Statement stmt : statements._elements)
		{
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt.getWhich());
				}
			}
		}

		generator.doneMethod();
	}
}
