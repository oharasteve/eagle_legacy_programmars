package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_And_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
import com.eagle.programmar.Python.Expressions.Python_Multiplicative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;

public class Python_Generator extends EagleGenerator
{
	private static Python_Expression wrap(AbstractToken token)
	{
		Python_Expression wrapper = new Python_Expression();
		wrapper.setWhich(token);
		return wrapper;
	}
	
	@Override
	public AbstractExpression newParenthesizedExpression(AbstractExpression expr)
	{
		return wrap(Python_Parenthesized_Expression.generateExpression(expr));
	}

	// Logical and, or, not
	
	@Override
	public AbstractExpression newAndExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Python_Logical_And_Expression.generateExpression(left, right));
	}
	
	@Override
	public AbstractExpression newOrExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Python_Logical_Or_Expression.generateExpression(left, right));
	}
	
	@Override
	public AbstractExpression newNotExpression(AbstractExpression expr)
	{
		return wrap(Python_Logical_Not_Expression.generateExpression(expr));
	}
	
	// Arithmetic plus, minus, times, divide, negative
	
	@Override
	public AbstractExpression newPlusExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Python_Additive_Expression.generateExpression(left, "+", right));
	}

	@Override
	public AbstractExpression newMinusExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Python_Additive_Expression.generateExpression(left, "-", right));
	}

	@Override
	public AbstractExpression newTimesExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Python_Multiplicative_Expression.generateExpression(left, "*", right));
	}

	@Override
	public AbstractExpression newDivideExpression(AbstractExpression left, AbstractExpression right)
	{
		return wrap(Python_Multiplicative_Expression.generateExpression(left, "//", right));
	}

	@Override
	public AbstractExpression newNegativeExpression(AbstractExpression expr)
	{
		return wrap(Python_Negative_Expression.generateExpression("-", expr));
	}
}
