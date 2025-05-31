// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 31, 2025

package com.eagle.programmar.Java.Functions;

import com.eagle.generate.Functions.EagleGenerateString;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_ToStringMethod extends PrecedenceOperator
		implements EagleRunnable, EagleGenerateString<Java_Expression>
{
	public @S(10) Java_Expression expression = new Java_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Java_Keyword TOSTRING = new Java_Keyword("toString");
	public @S(40) @NOSPACE PunctuationLeftParen leftParen;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expression);
		interpreter.pushStr(val.toString());
	}

	@Override
	public Java_Expression generateString(Java_Expression expr, AbstractToken source)
	{
		this.expression = expr;
		this.dot = new PunctuationPeriod();
		this.leftParen = new PunctuationLeftParen();
		this.rightParen = new PunctuationRightParen();
		
		this.setTransformationSource(source);
		return Java_Generator.wrapExpression(this);
	}
}
