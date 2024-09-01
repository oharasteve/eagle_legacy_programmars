package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
import com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;

public class CSharp_Generator extends EagleGenerator
{
	private static CSharp_Expression wrap(AbstractToken token)
	{
		CSharp_Expression wrapper = new CSharp_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	@Override
	public AbstractExpression newParenthesizedExpression(AbstractExpression expr)
	{
		return wrap(CSharp_ParenthesizedExpression.generateExpression(expr));
	}

	// Logical and, or, not

	@Override
	public AbstractExpression newAndExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(CSharp_LogicalAndExpression.generateExpression(left, right));
	}
	
	@Override
	public AbstractExpression newOrExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(CSharp_LogicalOrExpression.generateExpression(left, right));
	}
	
	@Override
	public AbstractExpression newNotExpression(AbstractExpression expr)
	{
		return wrap(CSharp_LogicalNotExpression.generateExpression(expr));
	}
	
	// Arithmetic plus, minus, times, divide, negative


	@Override
	public AbstractExpression newPlusExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(CSharp_AdditiveExpression.generateExpression(left, "+", right));
	}

	@Override
	public AbstractExpression newMinusExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(CSharp_AdditiveExpression.generateExpression(left, "-", right));
	}

	@Override
	public AbstractExpression newTimesExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(CSharp_MultiplicativeExpression.generateExpression(left, "*", right));
	}

	@Override
	public AbstractExpression newDivideExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(CSharp_MultiplicativeExpression.generateExpression(left, "/", right));
	}

	@Override
	public AbstractExpression newNegativeExpression(AbstractExpression expr)
	{
		return wrap(CSharp_NegativeExpression.generateExpression("-", expr));
	}
}
