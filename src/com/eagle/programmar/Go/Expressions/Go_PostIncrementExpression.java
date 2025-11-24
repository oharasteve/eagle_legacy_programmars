// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_PostIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Go_Variable var;
	public @S(20) Go_PunctuationChoice operator = new Go_PunctuationChoice("++", "--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		Go_Identifier_Reference id = var.vars.first();

		EagleValue val = interpreter.findSymbol(id.getValue());
		int prev = val.forceIntegerValue();

		int newVal;
		switch (operator.getValue())
		{
		case "++":
			newVal = prev + 1;
			break;
		case "--":
			newVal = prev - 1;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}

		EagleValue curr = new EagleInteger(newVal);
		interpreter.setSymbol(var, id.getValue(), curr);
		interpreter.pushInt(prev);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AssignmentEnum asg;
		switch (operator.getValue())
		{
		case "++":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "--":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + operator);
		}

		AbstractExpression one = generator.newNumberExpression("1", var);
		AbstractExpression asgExpr = generator.newAssignmentExpression(var.vars.first().getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, asg, one, this);
		return asgExpr;
	}
}
