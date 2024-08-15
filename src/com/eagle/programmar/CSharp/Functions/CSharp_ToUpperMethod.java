// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 2, 2024

package com.eagle.programmar.CSharp.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_ToUpperMethod extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) CSharp_KeywordChoice TOUPPER = new CSharp_KeywordChoice("ToLower", "ToUpper");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) @OPT CSharp_Expression expr;	// str.ToUpper() and Char.ToUpper(ch) are both ok
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String leftStr = interpreter.getStrValue(left);
		switch (TOUPPER.getValue())
		{
		case "ToLower":
			interpreter.pushStr(leftStr.toLowerCase());
			break;
		case "ToUpper":
			interpreter.pushStr(leftStr.toUpperCase());
			break;
		}
	}
}
