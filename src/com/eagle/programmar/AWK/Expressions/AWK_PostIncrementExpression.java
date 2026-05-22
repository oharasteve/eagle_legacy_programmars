// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.IncrementEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class AWK_PostIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) AWK_Variable var; // Cannot be just AWK_Expression -- infinite loop
	public @S(20) AWK_PunctuationChoice operator = new AWK_PunctuationChoice("++", "--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(var.id.getValue());
		int prev = val.forceIntegerValue();
		int curr;
		switch (operator.getValue())
		{
		case "++":
			curr = prev + 1;
			break;
		case "--":
			curr = prev - 1;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}
		interpreter.setSymbol(var, var.id.getValue(), new EagleInteger(curr));
		interpreter.pushInt(prev);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		IncrementEnum whichDirection;
		switch (operator.getValue())
		{
		case "++":
			whichDirection = IncrementEnum.INCREMENT;
			break;
		case "--":
			whichDirection = IncrementEnum.DECREMENT;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}
		return generator.newPostIncrementExpression(var.id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
	}
}
