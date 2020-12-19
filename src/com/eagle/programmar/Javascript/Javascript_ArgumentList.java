// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.PrecedenceChooser.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Javascript_ArgumentList extends PrimaryOperator
{
	public @S(10) Javascript_Expression arg;
	public @S(20) @OPT TokenList<Javascript_Comment> comment;
	public @S(30) @OPT TokenList<Javascript_MoreArguments> moreArgs;
	public @S(40) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
	
	public static class Javascript_MoreArguments extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT TokenList<Javascript_Comment> comment1;
		public @S(30) Javascript_Expression arg;
		public @S(40) @OPT TokenList<Javascript_Comment> comment2;
	}
}
