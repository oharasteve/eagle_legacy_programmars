// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.CSharp;

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
}
