// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_ArgumentList.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_MethodInvocation extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Variable methodName;
	public @S(20) @OPT CSharp_GenericType generic;
	public @S(30) @NOSPACE PunctuationLeftParen leftParen;
	public @S(40) @OPT @NOSPACE CSharp_ArgumentList argList;
	public @S(50) @NOSPACE PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Assume Console.WriteLine(expr.exp);
		CSharp_ArgumentOut argout = (CSharp_ArgumentOut) argList.arg.getWhich();
		EagleValue result = interpreter.getEagleValue(argout.arg);
		System.out.println(result.toString());
	}
}
