// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_LogicalOrExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_OrOperator operator;
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

	public static class Perl_OrOperator extends TokenChooser
	{
		public @CHOICE Perl_Punctuation XXorOperator = new Perl_Punctuation("||");
		public @CHOICE Perl_KeywordChoice XXOR = new Perl_KeywordChoice("or", "xor");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (operator.toString().equals("xor"))
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);
		}
		else
		{
			if (leftValue)
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(true);
			}
			else
			{
				boolean rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		LogicalOrEnum oper = LogicalOrEnum.OR;
		if (operator.toString().equals("xor"))
		{
			oper = LogicalOrEnum.XOR;
		}
		return generator.newLogicalOrExpression(leftExpr, oper, rightExpr, this);
	}
}
