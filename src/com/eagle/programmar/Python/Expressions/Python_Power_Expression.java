// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Power_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Python_Punctuation stars = new Python_Punctuation("**");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		double leftValue = interpreter.getDoubleValue(left);
		double rightValue = interpreter.getDoubleValue(right);
		interpreter.pushDouble(Math.pow(leftValue, rightValue));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newExponentExpression(leftExpr, rightExpr, this);
	}

	public static Python_Expression generateExpression(AbstractExpression leftExpr, AbstractExpression rightExpr,
			AbstractToken source)
	{
		Python_Power_Expression expr = new Python_Power_Expression();
		expr.left = (Python_Expression) leftExpr;
		expr.right = (Python_Expression) rightExpr;
		expr.setTransformationSource(source);
		return Python_Generator.wrapExpression(expr);
	}
}