// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.CSharp;

import java.util.ArrayList;

import com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CSharp_ArgumentList extends TokenSequence
{
	public @S(10) @OPT CSharp_Argument arg;
	public @S(20) @OPT TokenList<CSharp_MoreArguments> moreArgs;
	public @S(30) @OPT @CURIOUS("Extra comma") PunctuationComma comma;

	public static class CSharp_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT CSharp_Argument arg;
		public @S(30) @OPT TokenList<CSharp_Comment> comments;
	}
	
	public static CSharp_ArgumentList createArgumentList(ArrayList<CSharp_Expression> args)
	{
		if (args == null || args.size() == 0) return null;

		CSharp_ArgumentList argList = new CSharp_ArgumentList();
		boolean first = true;
		for (CSharp_Expression arg0 : args)
		{
			CSharp_Argument arg = new CSharp_Argument();
			CSharp_ArgumentOut out = new CSharp_ArgumentOut();
			out.arg = arg0;
			arg.setWhich(out);

			if (first)
			{
				first = false;
				argList.arg = arg;
				argList.arg.setPresent(true);
			}
			else
			{
				CSharp_MoreArguments more = new CSharp_MoreArguments();
				more.comma = new PunctuationComma();
				more.arg = arg;
				more.arg.setPresent(true);
				if (argList.moreArgs == null) argList.moreArgs = new TokenList<CSharp_MoreArguments>();
				argList.moreArgs.addToken(more);
			}
		}
		return argList;
	}
}
