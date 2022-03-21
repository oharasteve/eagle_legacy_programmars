// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 18, 2022

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ImportStatement extends TokenSequence
{
	public @S(10) @NEWLINE Javascript_Keyword IMPORT = new Javascript_Keyword("import");
	public @S(20) SeparatedList<Javascript_ImportElement, PunctuationComma> ids;
	public @S(30) @NOSPACE @OPT PunctuationSemicolon semicolon;
	
	public static class Javascript_ImportElement extends TokenSequence
	{
		public @S(10) Javascript_ImportWhat what;
		public @S(20) @OPT Javascript_ImportAs importAs;
		public @S(30) @OPT Javascript_ImportFrom importFrom;
		
		public static class Javascript_ImportWhat extends TokenChooser
		{
			public @CHOICE Javascript_Identifier_Reference theirId;
			public @CHOICE Javascript_Literal literal;
			
			public @CHOICE static class Javascript_ImportBraces extends TokenSequence
			{
				public @S(10) PunctuationLeftBrace leftBrace;
				public @S(20) Javascript_Identifier_Reference id;
				public @S(30) PunctuationRightBrace rightBrace;
			}
		}

		public static class Javascript_ImportAs extends TokenSequence
		{
			public @S(10) Javascript_Keyword AS = new Javascript_Keyword("as");
			public @S(20) Javascript_Identifier_Reference myId;
		}
		
		public static class Javascript_ImportFrom extends TokenSequence
		{
			public @S(10) Javascript_Keyword FROM = new Javascript_Keyword("from");
			public @S(20) Javascript_Literal where;
		}
	}
}