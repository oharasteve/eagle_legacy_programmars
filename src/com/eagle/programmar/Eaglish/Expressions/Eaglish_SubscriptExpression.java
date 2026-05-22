// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Eaglish_SubscriptExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Eaglish_Expression expr = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) Eaglish_Expression subscr;
	public @S(40) PunctuationRightBracket rightBracket;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArrayList<EagleValue> array = interpreter.getArrayValue(expr);
		int index = interpreter.getIntValue(subscr);
		EagleValue val = array.get(index);
		interpreter.pushEagleValue(val);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (expr.getWhich() instanceof Eaglish_VariableExpression)
		{
			Eaglish_VariableExpression varExpr = (Eaglish_VariableExpression) expr.getWhich();
			AbstractToken which = varExpr.variable.var.getWhich();
			if (which instanceof Eaglish_Identifier_Reference)
			{
				Eaglish_Identifier_Reference id = (Eaglish_Identifier_Reference) which;
				String varName = id.getValue();
				AbstractExpression subExpr = transformer.transformExpression(generator, subscr);
				return generator.newVariableExpression(varName, SubscriptEnum.FIRST_IS_ZERO, subExpr, expr);
			}
		}

		throw new RuntimeException("Unable to handle subscript");
	}
}