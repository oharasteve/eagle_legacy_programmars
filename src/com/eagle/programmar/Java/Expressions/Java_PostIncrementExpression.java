// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.IncrementEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_PostIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Variable var;
	public @S(20) @NOSPACE Java_PunctuationChoice operator =
			new Java_PunctuationChoice("++", "--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.firstId.getWhich() instanceof Java_Identifier_Reference)
		{
			Java_Identifier_Reference id = (Java_Identifier_Reference) var.firstId.getWhich();
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
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
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
		Java_Identifier_Reference id = (Java_Identifier_Reference) var.firstId.getWhich();
		return generator.newPostIncrementExpression(id.getValue(), SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
	}
	
	public Java_Expression generateIncrement(Java_Variable varName,
			IncrementEnum oper, AbstractToken source)
	{
		this.var = varName;
		switch (oper)
		{
		case INCREMENT:
			this.operator.setValue("++");
			break;
		case DECREMENT:
			this.operator.setValue("--");
			break;
		default:
			throw new RuntimeException("Unexpected operator: " + oper);
		}
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
