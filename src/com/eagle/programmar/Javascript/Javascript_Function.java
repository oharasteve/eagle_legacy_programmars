// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_Function extends TokenSequence
{
	public @S(10) @OPT Javascript_KeywordChoice STATIC = new Javascript_KeywordChoice("static", "async");
	public @S(20) @OPT Javascript_Keyword FUNCTION = new Javascript_Keyword("function");
	public @S(30) Javascript_FunctionImplementation implementation;
	
	public static class Javascript_FunctionImplementation extends TokenSequence
	{
		public @S(10) @OPT Javascript_Function_Definition functionName;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT Javascript_FunctionParameters params;
		public @S(40) @OPT TokenList<Javascript_Comment> comments1;
		public @S(50) PunctuationRightParen rightParen;
		public @S(60) @OPT TokenList<Javascript_Comment> comments2;
		public @S(70) Javascript_FunctionBody body;
	}
}
