// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.Java.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_EqualsMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Java_Expression left = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword EQUALS = new Java_Keyword("equals");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE Java_Expression expr;
	public @S(60) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		String other = interpreter.getStrValue(expr);
		interpreter.pushBool(leftStr.equals(other));
	}
	
	public static Java_EqualsMethod newEqualsMethod(Java_Expression leftExpr, Java_Expression rightExpr)
	{
		Java_EqualsMethod equals = new Java_EqualsMethod();
		equals.left = leftExpr;
		equals.dot = new PunctuationPeriod();
		equals.leftParen = new PunctuationLeftParen();
		equals.expr = rightExpr;
		equals.rightParen = new PunctuationRightParen();
		return equals;
	}
}
