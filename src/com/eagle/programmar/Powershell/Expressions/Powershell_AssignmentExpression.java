// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.generate.AssignmentEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.SubscriptEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.scope.EagleScope;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Powershell_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Powershell_PunctuationChoice operator = new Powershell_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=");
	public @S(30) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.getWhich() instanceof Powershell_VariableExpression)
		{
			Powershell_VariableExpression pVar = (Powershell_VariableExpression) var.getWhich();
			EagleValue newValue;
			switch (operator.getValue())
			{
			case "=":
				newValue = interpreter.getEagleValue(expr);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expr);
				EagleValue oldVar1 = interpreter.findSymbol(pVar.variable.id.toString());
				newValue = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expr);
				EagleValue oldVar2 = interpreter.findSymbol(pVar.variable.id.toString());
				newValue = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}

			if (pVar.variable.scope != null && pVar.variable.scope.isPresent())
			{
				// Was calling SetGlobalSymbol()
				EagleScope saveScope = interpreter._symbolTable.getScope();
				interpreter._symbolTable.setScope(interpreter._lang.getScope()); // Smash it :)
				interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
				interpreter._symbolTable.setScope(saveScope);
			}
			else
			{
				interpreter.setSymbol(var, pVar.variable.id.getValue(), newValue);
			}
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AssignmentEnum asg;
		switch (operator.getValue())
		{
		case "=":
			asg = AssignmentEnum.EQUALS;
			break;
		case "+=":
			asg = AssignmentEnum.PLUS_EQUALS;
			break;
		case "-=":
			asg = AssignmentEnum.MINUS_EQUALS;
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}

		if (!(var.getWhich() instanceof Powershell_VariableExpression))
		{
			throw new RuntimeException("Can only assign variables");
		}
		Powershell_VariableExpression variableExpr = (Powershell_VariableExpression) var.getWhich();
		Powershell_Variable theVar = variableExpr.variable;

		AbstractExpression subscrExpr = null;
		if (theVar.subscript != null && theVar.subscript.isPresent())
		{
			subscrExpr = transformer.transformExpression(generator, theVar.subscript.subscr);
		}

		AbstractExpression value = transformer.transformExpression(generator, expr);
		String newName = Powershell_Variable.repairName(theVar.id.getValue());
		AbstractExpression asgExpr = generator.newAssignmentExpression(newName,
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return asgExpr;
	}
}
