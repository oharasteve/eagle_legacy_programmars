// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Data extends TokenSequence
{
	public @S(10) @NEWLINE Javascript_Type type;
	public @S(20) Javascript_Variable_Definition var;
	public @S(30) @OPT Javascript_InitData init;
	public @S(40) @OPT TokenList<Javascript_More_Variables> moreVars;
	public @S(50) @OPT @NOSPACE PunctuationSemicolon semicolon;
	
	public static class Javascript_InitData extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Javascript_Expression expr;
	}
	
	public static class Javascript_More_Variables extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationComma comma;
		public @S(20) @OPT TokenList<Javascript_Comment> comments;
		public @S(30) Javascript_Variable_Definition var;
		public @S(40) @OPT Javascript_InitData init;
	}
}
