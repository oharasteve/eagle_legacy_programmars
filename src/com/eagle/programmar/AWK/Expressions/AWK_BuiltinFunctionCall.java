// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_BuiltinFunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_KeywordChoice function = new AWK_KeywordChoice("index", "int", "length", "match", "sprintf",
			"strcat", "strftime", "substr");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT AWK_ArgumentList argList;
	public @S(40) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String fnName = function.getValue();
		switch (fnName)
		{
		case "int":
			double intArg = interpreter.getDoubleValue(argList.expr);
			interpreter.pushInt((int) intArg);
			break;
		case "length":
			String lenArg = interpreter.getStrValue(argList.expr);
			interpreter.pushInt(lenArg.length());
			break;
		case "strcat":
			StringBuffer sb = new StringBuffer();
			sb.append(interpreter.getStrValue(argList.expr));
			for (AWK_MoreArguments arg : argList.more._elements)
			{
				sb.append(interpreter.getStrValue(arg.expr));
			}
			interpreter.pushStr(sb.toString());
			break;
		case "substr":
			String strArg = interpreter.getStrValue(argList.expr);
			int sc = interpreter.getIntValue(argList.more._elements.get(0).expr) - 1;
			int nc = interpreter.getIntValue(argList.more._elements.get(1).expr);
			if (sc > strArg.length()) throw new RuntimeException("Error on substr for " + strArg);
			if (sc + nc > strArg.length()) nc = strArg.length() - sc;
			interpreter.pushStr(strArg.substring(sc, sc + nc)); // AWK substr() starts with 1, not 0
			break;
		default:
			throw new RuntimeException("Unable to handle " + fnName);
		}
	}
}
