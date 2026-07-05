// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.IncrementEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_PostIncrementVariable extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) C_Variable var; // Cannot be just C_Expression -- infinite loop
	public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("++", "--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = var.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which + " now");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

		EagleValue val = interpreter.findSymbol(id.getValue());
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

		interpreter.setSymbol(var, id.getValue(), new EagleInteger(curr));
		interpreter.pushInt(prev);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractToken which = var.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which + " now");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

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
		return generator.newPostIncrementExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
	}
}
