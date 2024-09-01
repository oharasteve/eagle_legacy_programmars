package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
import com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression;
import com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
import com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;

public class Java_Generator extends EagleGenerator
{
	private static Java_Expression wrap(AbstractToken token)
	{
		Java_Expression wrapper = new Java_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	@Override
	public AbstractExpression newParenthesizedExpression(AbstractExpression expr)
	{
		return wrap(Java_ParenthesizedExpression.generateExpression(expr));
	}

	// Logical and, or, not

	@Override
	public AbstractExpression newAndExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Java_LogicalAndExpression.generateExpression(left, right));
	}
	
	@Override
	public AbstractExpression newOrExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Java_LogicalOrExpression.generateExpression(left, right));
	}
	
	@Override
	public AbstractExpression newNotExpression(AbstractExpression expr)
	{
		return wrap(Java_LogicalNotExpression.generateExpression(expr));
	}

	// Arithmetic plus, minus, times, divide, negative

	@Override
	public AbstractExpression newPlusExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Java_AdditiveExpression.generateExpression(left, "+", right));
	}

	@Override
	public AbstractExpression newMinusExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Java_AdditiveExpression.generateExpression(left, "-", right));
	}

	@Override
	public AbstractExpression newTimesExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Java_MultiplicativeExpression.generateExpression(left, "*", right));
	}

	@Override
	public AbstractExpression newDivideExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Java_MultiplicativeExpression.generateExpression(left, "/", right));
	}

	@Override
	public AbstractExpression newNegativeExpression(AbstractExpression expr)
	{
		return wrap(Java_NegativeExpression.generateExpression("-", expr));
	}
}
