// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Java_ArgumentList extends TokenSequence
{
	public @S(10) Java_Expression arg;
	public @S(20) @OPT TokenList<Java_Comment> comment;
	public @S(30) @OPT TokenList<Java_MoreArguments> moreArgs;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;

	public static class Java_MoreArguments extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT TokenList<Java_Comment> comment1;
		public @S(30) Java_Expression arg;
		public @S(40) @OPT TokenList<Java_Comment> comment2;
	}

	public static Java_ArgumentList createArgumentList(ArrayList<Java_Expression> args)
	{
		if (args == null || args.size() == 0) return null;

		Java_ArgumentList argList = new Java_ArgumentList();

		boolean first = true;
		for (AbstractExpression arg0 : args)
		{
			Java_Expression arg = (Java_Expression) arg0;

			if (first)
			{
				first = false;
				argList.arg = arg;
			}
			else
			{
				Java_MoreArguments more = new Java_MoreArguments();
				more.comma = new PunctuationComma();
				more.arg = arg;
				if (argList.moreArgs == null) argList.moreArgs = new TokenList<Java_MoreArguments>();
				argList.moreArgs.addToken(more);
			}
		}
		return argList;
	}
}
