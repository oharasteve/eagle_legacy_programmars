// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
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

public class Perl_PostIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Variable var;
	public @S(20) Perl_PunctuationChoice operator = new Perl_PunctuationChoice("++", "--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.getWhich() instanceof Perl_UserVariable)
		{
			Perl_UserVariable variable = (Perl_UserVariable) var.getWhich();
			EagleValue val = interpreter.findSymbol(variable.id.getValue());
			int prev = val.forceIntegerValue();
			EagleValue curr;
			switch (operator.toString())
			{
			case "++":
				curr = new EagleInteger(prev + 1);
				break;
			case "--":
				curr = new EagleInteger(prev - 1);
				break;
			default:
				throw new RuntimeException("Unexpected operator: " + operator);
			}
			interpreter.setSymbol(var, variable.id.getValue(), curr);
			interpreter.pushInt(prev);
		}
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

		if (var.getWhich() instanceof Perl_UserVariable)
		{
			Perl_UserVariable variable = (Perl_UserVariable) var.getWhich();
			String newName = Perl_Variable.repairName(variable.id.getValue());
			return generator.newPostIncrementExpression(newName,
					SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
		}
		throw new RuntimeException("Cannot handle variable: " + var);
	}
}
