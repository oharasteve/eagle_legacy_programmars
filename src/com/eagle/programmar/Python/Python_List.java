// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_List extends TokenSequence
{
	public @S(10) @OPT TokenList<Python_Comment> comment1;
	public @S(20) Python_Expression expr;
	public @S(30) @OPT TokenList<Python_MoreListItem> more;
	public @S(40) @OPT PunctuationComma comma;
	public @S(50) @OPT TokenList<Python_Comment> comment2;
	
	public static class Python_MoreListItem extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) @OPT TokenList<Python_Comment> comment;
		public @S(30) Python_Expression expr;
	}
}
