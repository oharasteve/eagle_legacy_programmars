// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Assignment;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class Java_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, Eagle_Generate_Assignment<Java_Expression>
{
	public @S(10) Java_Expression var = new Java_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Java_PunctuationChoice operator = new Java_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=", "<<=",
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
	public Java_Expression generateAssignment(Java_Expression varExpr,
			AssignmentEnum oper, Java_Expression expression, AbstractToken source)
	{
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
		this.var = varExpr;
		this.operator.setValue(punct);
		this.expr = expression;
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
