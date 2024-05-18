// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.AWK_ArgumentList;
import com.eagle.programmar.AWK.AWK_ArgumentList.AWK_MoreArguments;
import com.eagle.programmar.AWK.Terminals.AWK_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_PrintStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("#print") AWK_KeywordChoice PRINT = new AWK_KeywordChoice("print", "printf");
	public @S(20) AWK_PrintParameters param;
	
	public static class AWK_PrintParameters extends TokenChooser
	{
		public @FIRST AWK_Print_WithParens withParens;
		public @CHOICE AWK_Print_NoParens noParens;
	}

	public static class AWK_Print_WithParens extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT AWK_ArgumentList argList;
		public @S(30) PunctuationRightParen rightParen;
	}
	
	public static class AWK_Print_NoParens extends TokenSequence
	{
		public @S(10) @OPT AWK_ArgumentList argList;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AWK_ArgumentList args;
		if (param.getWhich() instanceof AWK_Print_WithParens)
		{
			args = ((AWK_Print_WithParens) param.getWhich()).argList;
		}
		else if (param.getWhich() instanceof AWK_Print_NoParens)
		{
			args = ((AWK_Print_NoParens) param.getWhich()).argList;
		}
		else throw new RuntimeException("Unexpected print argument: " + param.toString());
		
		String result = interpreter.getStrValue(args.expr);
		System.out.print(result);
		for (AWK_MoreArguments nxt : args.more._elements)
		{
			result = interpreter.getStrValue(nxt.expr);
			System.out.print(result);
		}
		System.out.println();
	}
}
