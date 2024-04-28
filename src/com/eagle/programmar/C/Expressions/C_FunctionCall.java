// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Generic;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) @OPT CPlus_NamespaceList namespace;
	public @S(20) C_Variable functionName;
	public @S(30) @OPT C_Generic generic;
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT C_ArgumentList argList;
	public @S(60) PunctuationRightParen rightParen;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Assume printf(fmt, vals)
		AbstractToken which = argList.moreArgs.first().arg.getWhich();
		if (which instanceof C_Expression)
		{
			C_Expression expr = (C_Expression) which;
			EagleValue result = interpreter.getEagleValue(expr.getWhich());
			System.out.println(result.toString());
		}
	}
}
