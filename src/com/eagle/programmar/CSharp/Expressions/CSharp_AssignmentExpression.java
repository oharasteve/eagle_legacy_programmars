// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.EagleGenerator.AssignmentEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Assignment;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;

public class CSharp_AssignmentExpression extends PrecedenceOperator
		implements EagleRunnable, Eagle_Generate_Assignment<CSharp_Expression>
{
	public @S(10) CSharp_Expression var = new CSharp_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("=", "*=", "/=", "%=", "+=", "-=",
			"<<=", ">>=", ">>>=", "&=", "^=", "|=");
	public @S(30) CSharp_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(var.getWhich() instanceof CSharp_VariableExpression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + var.getWhich());
		}

		CSharp_VariableExpression varExpr = (CSharp_VariableExpression) var.getWhich();
		AbstractToken token = varExpr.variable.firstId.getWhich();
		if (token instanceof CSharp_Identifier_Reference)
		{
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) token;
			switch (operator.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(var, id.getValue(), val);
				break;
			case "+=":
				int newVal = interpreter.getIntValue(expr);
				EagleValue oldVar = interpreter.findSymbol(id.toString());
				EagleInteger newValue = new EagleInteger(newVal + oldVar.forceIntegerValue());
				interpreter.setSymbol(var, id.getValue(), newValue);
				break;
			default:
				throw new RuntimeException("Unexpected assignment operator: " + operator.getValue());
			}
		}
	}
	
	@Override
	public CSharp_Expression generateAssignment(CSharp_Expression varExpr,
			AssignmentEnum oper, CSharp_Expression expression,
			AbstractToken source)
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
		return CSharp_Generator.wrapExpression(this);
	}
}
