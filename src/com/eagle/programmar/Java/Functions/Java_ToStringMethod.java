// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2025

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator1Metrics.Oper1Types;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
import com.eagle.programmar.Java.Expressions.Java_VariableExpression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_ToStringMethod extends PrecedenceOperator
		implements EagleRunnable
{
	public @S(10) Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword TOSTRING = new Java_Keyword("toString");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @OPT @NOSPACE Java_Expression value;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		interpreter.pushStr(val.toString());
	}

	public Java_Expression generateString(Oper1Types types, Java_Expression expr, AbstractToken source)
	{
		if (types != null && types._type1.equals(EagleInteger.INTEGER))
		{
			// Java does not like 'ok.toString()' where 'ok' is an int instead of an Integer.
			Java_VariableExpression varExpr = new Java_VariableExpression();
			varExpr.variable = Java_Variable.newVariable("Integer");
			expression = Java_Generator.wrapExpression(varExpr);
			dot = new PunctuationPeriod();
			leftParen = new PunctuationLeftParen();
			value = expr;
			value.setPresent(true);
			rightParen = new PunctuationRightParen();
			return Java_Generator.wrapExpression(this);
		}
		
		Java_ParenthesizedExpression parens = new Java_ParenthesizedExpression();
		return parens.generateParentheses(expr, source);
	}
}
