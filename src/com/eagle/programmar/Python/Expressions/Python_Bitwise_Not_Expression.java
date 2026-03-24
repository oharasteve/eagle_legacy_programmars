// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 8, 2025

package com.eagle.programmar.Python.Expressions;

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
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Bitwise_Not_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Punctuation operator = new Python_Punctuation('~');
	public @S(20) Python_Expression expr = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int value = interpreter.getIntValue(expr);
		interpreter.pushInt(~value);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression value = transformer.transformExpression(generator, expr);
		return generator.newBitwiseNotExpression(value, this);
	}

	public static Python_Expression generateBitwiseNot(Python_Expression value, AbstractToken source)
	{
		Python_Bitwise_Not_Expression bitExpr = new Python_Bitwise_Not_Expression();
		bitExpr.expr = value;
		bitExpr.setTransformationSource(source);
		return Python_Generator.wrapExpression(bitExpr);
	}
}
