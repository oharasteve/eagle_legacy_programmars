// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Variable methodName;
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE @OPT Java_ArgumentList argList;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Assume System.out.println(expr);
		EagleValue result = interpreter.getEagleValue(argList.arg);
		System.out.println(result.toString());
	}
}
