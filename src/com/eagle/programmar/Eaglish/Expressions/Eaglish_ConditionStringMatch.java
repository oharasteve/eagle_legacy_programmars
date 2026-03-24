// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubstringSCEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_ConditionStringMatch extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_KeywordChoice matchOperator = new Eaglish_KeywordChoice("ENDS_WITH", "STARTS_WITH");
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	public @S(40) @OPT Eaglish_Condition_MatchAt atClause;

	public static class Eaglish_Condition_MatchAt extends TokenSequence
	{
		public @S(10) Eaglish_Keyword AT = new Eaglish_Keyword("AT");
		public @S(20) Eaglish_Expression position;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String rightStr = interpreter.getStrValue(right);
		String oper = matchOperator.getValue();

		int sc = 0;
		if (atClause != null && atClause.isPresent())
		{
			sc = interpreter.getIntValue(atClause.position);
		}

		switch (oper)
		{
		case "ENDS_WITH":
			interpreter.pushBool(leftStr.endsWith(rightStr));
			return;
		case "STARTS_WITH":
			interpreter.pushBool(leftStr.startsWith(rightStr, sc));
			return;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		String oper = matchOperator.toString();

		AbstractExpression scExpr = null;
		if (atClause != null && atClause.isPresent())
		{
			scExpr = transformer.transformExpression(generator, atClause.position);
		}

		switch (oper.toUpperCase())
		{
		case "ENDS_WITH":
			return generator.newEndsWithFunction(leftExpr, rightExpr, this);
		case "STARTS_WITH":
			return generator.newStartsWithFunction(leftExpr, rightExpr, scExpr, SubstringSCEnum.FIRST_CHAR_IS_ZERO,
					this);
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
	}
}