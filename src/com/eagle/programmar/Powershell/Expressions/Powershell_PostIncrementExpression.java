// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.IncrementEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_PostIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Powershell_Variable var;
	public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice("++", "--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.findSymbol(var.id.getValue());
		int prev = val.forceIntegerValue();
		EagleValue curr;
		switch (operator.getValue())
		{
		case "++":
			curr = new EagleInteger(prev + 1);
			break;
		case "--":
			curr = new EagleInteger(prev - 1);
			break;
		default:
			throw new RuntimeException("Unable to handle: " + operator);
		}
		interpreter.setSymbol(var, var.id.getValue(), curr);
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

		String newName = Powershell_Variable.repairName(var.id.getValue());
		return generator.newPostIncrementExpression(newName,
				SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
	}
}
