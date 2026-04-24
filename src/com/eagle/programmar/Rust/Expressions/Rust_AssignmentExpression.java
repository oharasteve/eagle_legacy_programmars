// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2022

package com.eagle.programmar.Rust.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Rust.Rust_Expression;
import com.eagle.programmar.Rust.Rust_Generator;
import com.eagle.programmar.Rust.Rust_Variable;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Symbols.Rust_Identifier_Reference;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_PunctuationChoice;
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

public class Rust_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Rust_Expression var = new Rust_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Rust_PunctuationChoice operator = new Rust_PunctuationChoice("=", "+=", "-=");
	public @S(30) Rust_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(var.getWhich() instanceof Rust_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}
		Rust_VariableExpression varExpr = (Rust_VariableExpression) var.getWhich();

		String id = varExpr.variable.var.getValue();
		switch (operator.getValue())
		{
		case "=":
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, id, val);
			break;
		case "+=":
			int newVal1 = interpreter.getIntValue(expr);
			EagleValue oldVar1 = interpreter.findSymbol(id);
			EagleInteger newValue1 = new EagleInteger(oldVar1.forceIntegerValue() + newVal1);
			interpreter.setSymbol(var, id, newValue1);
			break;
		case "-=":
			int newVal2 = interpreter.getIntValue(expr);
			EagleValue oldVar2 = interpreter.findSymbol(id);
			EagleInteger newValue2 = new EagleInteger(oldVar2.forceIntegerValue() - newVal2);
			interpreter.setSymbol(var, id, newValue2);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (!(var.getWhich() instanceof Rust_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}
		Rust_VariableExpression varExpr = (Rust_VariableExpression) var.getWhich();

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

		AbstractExpression value = transformer.transformExpression(generator, expr);
		AbstractExpression asgExpr = generator.newAssignmentExpression(varExpr.variable.var.getValue(),
				SubscriptEnum.FIRST_IS_ZERO, null, asg, value, this);
		return asgExpr;
	}
	
	public static Rust_Expression generateAssignment(Rust_Variable variable, Rust_Expression subscript,
			AssignmentEnum oper, Rust_Expression expression, AbstractToken source)
	{
		Rust_AssignmentExpression asgExpr = new Rust_AssignmentExpression();
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

		Rust_Identifier_Reference id = variable.var;
		Rust_VariableExpression varExpr = new Rust_VariableExpression();
		varExpr.variable = Rust_Variable.generateVariable(id.getValue());
		asgExpr.var = Rust_Generator.wrapExpression(varExpr);
		asgExpr.operator.setValue(punct);
		
		if (expression.getWhich() instanceof Rust_Literal)
		{
			asgExpr.expr = Rust_ToStringMethod.generateString(expression, source);
		}
		else
		{
			asgExpr.expr = expression;
		}
		
		asgExpr.setTransformationSource(source);
		return Rust_Generator.wrapExpression(asgExpr);
	}
}
