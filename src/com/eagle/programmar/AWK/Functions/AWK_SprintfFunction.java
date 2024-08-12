// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Functions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_SprintfFunction extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Keyword SPRINTF = new AWK_Keyword("sprintf");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		StringBuffer sb = new StringBuffer();
		// sb.append(interpreter.getStrValue(argList.expr)); // Skip the format for now
		for (AWK_MoreArguments arg : argList.more._elements)
		{
			sb.append(interpreter.getStrValue(arg.expr));
		}
		interpreter.pushStr(sb.toString());
	}
}
