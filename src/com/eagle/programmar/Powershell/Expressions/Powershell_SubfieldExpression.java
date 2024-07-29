// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_ExpressionList;
import com.eagle.programmar.Powershell.Symbols.Powershell_Variable_Reference;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_SubfieldExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Powershell_Variable_Reference right;
	public @S(40) @OPT Powershell_SubfieldArgs args;
	
	public static class Powershell_SubfieldArgs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Powershell_ExpressionList arguments;
		public @S(30) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (right.getValue())
		{
		case "length":
			String str1 = interpreter.getStrValue(left);
			interpreter.pushInt(str1.length());
			return;
		case "startswith":
			if (args != null && args.isPresent())
			{
				if (args.arguments != null && args.arguments.isPresent())
				{
					String str2 = interpreter.getStrValue(left);
					String patt = interpreter.getStrValue(args.arguments.expr);
					int sc = 0;
					if (args.arguments.more != null && args.arguments.more.isPresent() && args.arguments.more.size() > 0)
					{
						sc = interpreter.getIntValue(args.arguments.more.first().expr);
					}
					interpreter.pushBool(str2.startsWith(patt, sc));
					return;
				}
			}
			break;
		case "substring":
			if (args != null && args.isPresent())
			{
				if (args.arguments != null && args.arguments.isPresent())
				{
					String str3 = interpreter.getStrValue(left);
					int sc = interpreter.getIntValue(args.arguments.expr);
					int ec = str3.length();
					if (args.arguments.more != null && args.arguments.more.isPresent() && args.arguments.more.size() > 0)
					{
						ec = interpreter.getIntValue(args.arguments.more._elements.get(0).expr);
					}
					interpreter.pushStr(str3.substring(sc, ec));
					return;
				}
			}
			break;
		}
		
		throw new RuntimeException("Unable to find method " + right.getValue());
	}
}
