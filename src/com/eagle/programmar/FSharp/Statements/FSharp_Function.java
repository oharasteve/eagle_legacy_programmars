// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.FSharp.FSharp_Element;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
import com.eagle.programmar.FSharp.FSharp_Syntax;
import com.eagle.programmar.FSharp.FSharp_Type;
import com.eagle.programmar.FSharp.Symbols.FSharp_Function_Definition;
import com.eagle.programmar.FSharp.Symbols.FSharp_Variable_Definition;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class FSharp_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @DOC("functions/") FSharp_Keyword LET = new FSharp_Keyword("let");
	public @S(20) FSharp_Function_Definition id;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<FSharp_FunctionParam, PunctuationComma> params;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) @OPT FSharp_ReturnType returnType;
	public @S(70) PunctuationEquals equals;
	public @S(80) FSharp_EndOfLine eoln;
	public @S(90) TokenList<FSharp_Element> statements;

	public static class FSharp_FunctionParam extends TokenSequence
	{
		public @S(10) FSharp_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) FSharp_Type type;
	}

	public static class FSharp_ReturnType extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) FSharp_Type type;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, FSharp_Syntax.IS_CASE_SENSITIVE);

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
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum typRet = TypeEnum.VOID;
		if (returnType != null && returnType.isPresent())
		{
			typRet = returnType.type.findType();
		}
		if (typRet == TypeEnum.VOID)
		{
			typRet = transformer.findReturnMetric(id);
		}
		AbstractType newReturnType = generator.transformType(typRet, null, id);

		String fnName = id.getValue();

		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found F# function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (params != null && params.isPresent())
		{
			for (int i = 0; i < params.getPrimaryCount(); i++)
			{
				FSharp_FunctionParam param = params.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, param);
				}

				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}

		for (FSharp_Element elt : statements._elements)
		{
			AbstractToken which1 = elt.statementOrComment.getWhich();
			if (which1 instanceof FSharp_Statement_List)
			{
				FSharp_Statement_List stmtList = (FSharp_Statement_List) which1;
				int numStmts = stmtList.statements.getPrimaryCount();
				for (int i = 0; i < numStmts; i++)
				{
					FSharp_Statement stmt = stmtList.statements.getPrimaryElement(i);
					AbstractToken which2 = stmt.getWhich();

					if (i == numStmts - 1)
					{
						// Last line in a function *might* be an implied RETURN
						if (which2 instanceof FSharp_ExpressionStatement)
						{
							FSharp_ExpressionStatement exprStmt = (FSharp_ExpressionStatement) which2;
							AbstractExpression newExpr = transformer.transformExpression(generator,
									exprStmt.expression);
							AbstractStatement retStmt = generator.newReturnStatement(newExpr, which2);
							generator.addStatement(retStmt, stmt);
							break; // Only gets here for the last statement in the Function
						}
					}

					Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which2);
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmt.getWhich());
						}
					}
				}
			}
		}

		generator.doneMethod();
	}
}
