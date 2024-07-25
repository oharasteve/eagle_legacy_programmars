// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 17, 2022

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Terminals.Python_EndOfLine;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_Decorators extends TokenSequence
{
	public @S(10) Python_Decorator decorator;
	public @S(20) @OPT TokenList<Python_MoreDecorators> moreDecorators;

	public static class Python_MoreDecorators extends TokenChooser
	{
		public @CHOICE Python_Decorator decorator;
		public @CHOICE Python_CommentEoln comment;
	}

	public static class Python_Decorator extends TokenSequence
	{
		public @S(10) Python_Punctuation atSign = new Python_Punctuation('@');
		public @S(20) Python_Expression expr;
		public @S(30) @OPT Python_CommentEoln comment;
		public @S(40) @OPT Python_EndOfLine newLine;
	}
}