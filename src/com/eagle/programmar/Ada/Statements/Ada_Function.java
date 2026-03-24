// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Syntax;
import com.eagle.programmar.Ada.Ada_Type;
import com.eagle.programmar.Ada.Symbols.Ada_Function_Definition;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Symbols.Ada_Variable_Definition;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Ada_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) Ada_Keyword FUNCTION = new Ada_Keyword("function");
	public @S(20) Ada_Function_Definition id;
	public @S(30) @OPT Ada_FunctionParams funcParamDefs;
	public @S(40) @OPT Ada_FunctionReturns returns;
	public @S(50) Ada_Keyword IS = new Ada_Keyword("is");
	public @S(60) TokenList<Ada_Statement> statements1;
	public @S(70) Ada_Keyword BEGIN = new Ada_Keyword("begin");
	public @S(80) TokenList<Ada_Statement> statements2;
	public @S(90) Ada_Keyword END = new Ada_Keyword("end");
	public @S(100) @OPT Ada_Identifier_Reference id2;
	public @S(110) PunctuationSemicolon semicolon;

	public static class Ada_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Ada_Parameter, PunctuationSemicolon> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Ada_Parameter extends TokenSequence
	{
		public @S(10) Ada_Variable_Definition param;
		public @S(20) PunctuationColon colon;
		public @S(30) Ada_Type type;
	}

	public static class Ada_FunctionReturns extends TokenSequence
	{
		public @S(10) Ada_Keyword RETURN = new Ada_Keyword("return");
		public @S(20) Ada_Type type;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Ada_Syntax.IS_CASE_SENSITIVE);

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

		// Nothing to do here. Ignore the function definitions
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractType newReturnType = null;
		if (returns != null && returns.isPresent())
		{
			newReturnType = returns.type.convertType(generator);
		}

		if (newReturnType == null)
		{
			TypeEnum metricRetType = transformer.findReturnMetric(id);
			newReturnType = generator.transformType(metricRetType, null, id);
		}

		String fnName = id.getValue();

		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Ada function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);

		if (funcParamDefs != null && funcParamDefs.isPresent())
		{
			if (funcParamDefs.parameters != null && funcParamDefs.parameters.isPresent())
			{
				for (int i = 0; i < funcParamDefs.parameters.getPrimaryCount(); i++)
				{
					Ada_Parameter param = funcParamDefs.parameters.getPrimaryElement(i);
					AbstractType paramType = null;

					if (argTypes != null && i < argTypes.size())
					{
						String metricArgType = argTypes.get(i);
						TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
						paramType = generator.transformType(metricArg, null, param);
					}

					generator.addMethodParameter(paramType, param.param.getValue());
				}
			}
		}

		for (Ada_Statement stmt1 : statements1._elements)
		{
			Collection<AbstractStatement> newStmts1 = transformer.transformStatement(generator, stmt1.getWhich());
			if (newStmts1 != null)
			{
				for (AbstractStatement newStmt1 : newStmts1)
				{
					generator.addStatement(newStmt1, stmt1.getWhich());
				}
			}
		}

		for (Ada_Statement stmt2 : statements2._elements)
		{
			Collection<AbstractStatement> newStmts2 = transformer.transformStatement(generator, stmt2.getWhich());
			if (newStmts2 != null)
			{
				for (AbstractStatement newStmt2 : newStmts2)
				{
					generator.addStatement(newStmt2, stmt2.getWhich());
				}
			}
		}

		generator.doneMethod();
	}
}
