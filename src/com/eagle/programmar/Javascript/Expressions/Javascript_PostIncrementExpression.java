// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.tokens.AbstractToken;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.generate.IncrementEnum;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Javascript_PostIncrementExpression extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Javascript_Variable var;
	public @S(20) @NOSPACE Javascript_PunctuationChoice operator = new Javascript_PunctuationChoice("++", "--");

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
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) var.firstId.getWhich();
		return generator.newPostIncrementExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, whichDirection, this);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		JsRuntime rt = JsRuntime.of(interpreter);
		EagleValue prev = Javascript_Variable.evaluate(rt, var, null, 0);
		EagleValue next = JsValues.num(JsValues.toNumber(prev) + ("++".equals(operator.getValue()) ? 1 : -1));
		AbstractToken last = Javascript_Variable.lastQualifier(var);
		if (last == null) rt.assign(var, Javascript_Variable.firstName(var), next);
		else
		{
			EagleValue receiver = Javascript_Variable.evaluate(rt, var, null, 1);
			if (last instanceof Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField)
				rt.setProperty(receiver, ((Javascript_Variable.Javascript_VariableQualifier.Javascript_VarField) last).id.getValue(), next);
			else rt.setIndex(receiver, rt.eval(((com.eagle.programmar.Javascript.Javascript_Subscript) last).expr), next);
		}
		interpreter.pushEagleValue(prev);
	}
}
