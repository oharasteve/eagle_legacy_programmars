// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Subscript;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AdditiveEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_VariableExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) CSharp_Variable variable;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(variable);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression subscript = null;
		if (variable.subscript != null && variable.subscript.size() > 0)
		{
			CSharp_Subscript first = variable.subscript.first();
			subscript = transformer.transformExpression(generator, first.expr);
		}
		AbstractToken which = variable.firstId.getWhich();
		if (!(which instanceof CSharp_Identifier_Reference))
		{
			throw new RuntimeException("Cannot handle variable: " + which);
		}
		CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) which;
		return generator.newVariableExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscript, this);
	}

	public static CSharp_Expression generateVarExpr(String name, SubscriptEnum offset,
			CSharp_Expression subscrExpr, AbstractToken source)
	{
		CSharp_VariableExpression varExpr = new CSharp_VariableExpression();
		varExpr.variable = CSharp_Variable.newVariable(name);

		if (subscrExpr != null)
		{
			CSharp_Subscript subscript = new CSharp_Subscript();
			subscript.leftBracket = new PunctuationLeftBracket();

			if (offset == SubscriptEnum.FIRST_IS_ONE)
			{
				CSharp_Expression one = CSharp_Generator.wrapExpression(
						CSharp_Number.generateNumber("1", source));
				Oper2Types types = new Oper2Types(EagleInteger.INTEGER, EagleInteger.INTEGER);
				CSharp_Expression minusOne = CSharp_AdditiveExpression.generateAdditive(types, subscrExpr,
						AdditiveEnum.MINUS, one, source);
				subscript.expr = minusOne;
			}
			else
			{
				subscript.expr = subscrExpr;
			}
			subscript.expr.setPresent(true);
			subscript.rightBracket = new PunctuationRightBracket();

			varExpr.variable.subscript = new TokenList<CSharp_Subscript>();
			varExpr.variable.subscript.addToken(subscript);
		}

		varExpr.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(varExpr);
	}
}
