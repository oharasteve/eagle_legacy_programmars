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
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Java_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice(
			"=", "*=", "/=", "%=", "+=", "-=", "<<=",
			">>=", ">>>=", "&=", "^=", "|=");
	public @S(30) Java_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(var.getWhich() instanceof Java_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}

		Java_VariableExpression varExpr = (Java_VariableExpression) var.getWhich();
		AbstractToken token = varExpr.variable.firstId.getWhich();
		if (token instanceof Java_Identifier_Reference)
		{
			Java_Identifier_Reference id = (Java_Identifier_Reference) token;
			switch (operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(var, id.getValue(), val);
				break;
			case "+=":
				int newVal1 = interpreter.getIntValue(expr);
				EagleValue oldVar1 = interpreter.findSymbol(id.toString());
				EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
				interpreter.setSymbol(var, id.getValue(), newValue1);
				break;
			case "-=":
				int newVal2 = interpreter.getIntValue(expr);
				EagleValue oldVar2 = interpreter.findSymbol(id.toString());
				EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
				interpreter.setSymbol(var, id.getValue(), newValue2);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

		if (!(var.getWhich() instanceof Java_VariableExpression))
		{
			throw new RuntimeException("Can only assign variables");
		}
		Java_VariableExpression variableExpr = (Java_VariableExpression) var.getWhich();
		Java_Variable theVar = variableExpr.variable;

		AbstractExpression subscrExpr = null;
		if (theVar.subscript != null && theVar.subscript.size() > 0)
		{
			subscrExpr = transformer.transformExpression(generator, theVar.subscript.first().expr);
		}

		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractToken which = theVar.firstId.getWhich();
		if (!(which instanceof Java_Identifier_Reference))
		{
			throw new RuntimeException("Have to assign to a regular variable");
		}
		Java_Identifier_Reference id = (Java_Identifier_Reference) which;

		AbstractExpression asgExpr = generator.newAssignmentExpression(id.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, subscrExpr, asg, value, this);
		return asgExpr;
	}

	public static Java_Expression generateAssignment(Java_Variable variable, Java_Expression subscript,
			AssignmentEnum oper, Java_Expression expression, AbstractToken source)
	{
		Java_AssignmentExpression asgExpr = new Java_AssignmentExpression();
		String punct;
		switch (oper)
		{
		case EQUALS:
			punct = "=";
			break;
		case PLUS_EQUALS:
			punct = "+=";
			break;
		case MINUS_EQUALS:
			punct = "-=";
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + oper);
		}

		AbstractToken which = variable.firstId.getWhich();
		if (!(which instanceof Java_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which);
		}
		Java_Identifier_Reference id = (Java_Identifier_Reference) which;

		asgExpr.var = Java_VariableExpression.generateVariableExpression(id.getValue(), SubscriptEnum.FIRST_IS_ZERO, subscript, source);
		asgExpr.operator.setValue(punct);
		asgExpr.expr = expression;
		asgExpr.setTransformationSource(source);
		return Java_Generator.wrapExpression(asgExpr);
	}
}
