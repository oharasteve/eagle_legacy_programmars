// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class VB_ArgumentList extends TokenSequence
{
	public @S(10) VB_Expression arg;
	public @S(20) @OPT TokenList<VB_Comment> comment;
	public @S(30) @OPT TokenList<VB_MoreArguments> moreArgs;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;

	public static class VB_MoreArguments extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<VB_Comment> comment1;
		public @S(30) VB_Expression arg;
		public @S(40) @OPT TokenList<VB_Comment> comment2;
	}
}
