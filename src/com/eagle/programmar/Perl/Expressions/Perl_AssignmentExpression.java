// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Perl_AssignmentExpression extends PrecedenceOperator
	implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Perl_Expression var = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Perl_PunctuationChoice operator = new Perl_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=", "<<=",
			">>=", ">>>=", "&=", "^=", "|=", ".=");
	public @S(30) Perl_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(var.getWhich() instanceof Perl_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}
		Perl_VariableExpression varExpr = (Perl_VariableExpression) var.getWhich();
		if (!(varExpr.variable.getWhich() instanceof Perl_UserVariable))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}

		Perl_UserVariable userVar = (Perl_UserVariable) varExpr.variable.getWhich();
		switch (operator.getValue())
		{
		case "=":
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, userVar.id.getValue(), val);
			break;
		case "+=":
			int newVal1 = interpreter.getIntValue(expr);
			EagleValue oldVar1 = interpreter.findSymbol(userVar.id.toString());
			EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
			interpreter.setSymbol(var, userVar.id.getValue(), newValue1);
			break;
		case "-=":
			int newVal2 = interpreter.getIntValue(expr);
			EagleValue oldVar2 = interpreter.findSymbol(userVar.id.toString());
			EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
			interpreter.setSymbol(var, userVar.id.getValue(), newValue2);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
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

		if (! (var.getWhich() instanceof Perl_VariableExpression))
		{
			throw new RuntimeException("Can only assign variables");
		}
		Perl_VariableExpression variableExpr = (Perl_VariableExpression) var.getWhich();
		Perl_Variable theVar = variableExpr.variable;
		if (! (theVar.getWhich() instanceof Perl_UserVariable))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}
		Perl_UserVariable userVar = (Perl_UserVariable) theVar.getWhich();

		AbstractExpression subscrExpr = null;
		if (userVar.subscript != null && userVar.subscript.size() > 0)
		{
			subscrExpr = transformer.transformExpression(generator, userVar.subscript.first().expr);
		}
		
		AbstractExpression value = transformer.transformExpression(generator, expr);
		Perl_Identifier_Reference id = userVar.id;
		
		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return asgExpr;
	}
}
