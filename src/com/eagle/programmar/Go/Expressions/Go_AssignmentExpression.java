// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Go_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Go_Expression varExpr = new Go_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Go_PunctuationChoice equals = new Go_PunctuationChoice("=", ":=", "*=", "/=", "%=", "+=", "-=");
	public @S(30) Go_Expression expr = new Go_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(varExpr.getWhich() instanceof Go_VariableExpression))
		{
			throw new RuntimeException("Can only handle simple assignments, not  " + varExpr.getWhich());
		}

		Go_Variable variable = ((Go_VariableExpression) varExpr.getWhich()).variable;
		EagleValue val = interpreter.getEagleValue(expr);
		Go_Identifier_Reference id = variable.vars.first();
		switch (equals.getValue())
		{
		case "=", ":=":
			interpreter.setSymbol(id, id.toString(), val);
			return;
		case "+=":
			EagleValue oldValue1 = interpreter.findSymbol(id.getValue());
			int newVal1 = oldValue1.forceIntegerValue() + val.forceIntegerValue();
			interpreter.setSymbol(id, id.toString(), new EagleInteger(newVal1));
			return;
		case "-=":
			EagleValue oldValue2 = interpreter.findSymbol(id.getValue());
			int newVal2 = oldValue2.forceIntegerValue() - val.forceIntegerValue();
			interpreter.setSymbol(id, id.toString(), new EagleInteger(newVal2));
			return;
		}
		throw new RuntimeException("Unable to handle assignment operator: " + equals.getValue());
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		if (!(varExpr.getWhich() instanceof Go_VariableExpression))
		{
			throw new RuntimeException("Can only handle simple assignments, not  " + varExpr.getWhich());
		}

		Go_Variable variable = ((Go_VariableExpression) varExpr.getWhich()).variable;
		AssignmentEnum asg;
		switch (equals.getValue())
		{
		case "=", ":=":
			asg = AssignmentEnum.EQUALS;
			break;
		case "+=":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "-=":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + equals);
		}

		AbstractExpression subscrExpr = null;
		if (variable.subscript != null && variable.subscript.isPresent())
		{
			subscrExpr = transformer.transformExpression(generator, variable.subscript.expr);
		}
		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(variable.vars.first().getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return asgExpr;
	}
}
