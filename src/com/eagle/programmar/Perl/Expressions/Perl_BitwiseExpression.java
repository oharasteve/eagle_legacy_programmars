// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BitwiseEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_BitwiseExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_PunctuationChoice operator = new Perl_PunctuationChoice("&", "|", "^");
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		int result;
		switch (operator.toString())
		{
		case "&":
			result = leftValue & rightValue;
			break;
		case "|":
			result = leftValue | rightValue;
			break;
		case "^":
			result = leftValue ^ rightValue;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}
		interpreter.pushInt(result);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "&":
			return generator.newBitwiseExpression(leftExpr, BitwiseEnum.AND, rightExpr, this);
		case "|":
			return generator.newBitwiseExpression(leftExpr, BitwiseEnum.OR, rightExpr, this);
		case "^":
			return generator.newBitwiseExpression(leftExpr, BitwiseEnum.XOR, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}
	}
}
