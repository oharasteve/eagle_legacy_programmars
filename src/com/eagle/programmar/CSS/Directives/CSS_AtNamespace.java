// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSS_AtNamespace extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword NAMESPACE = new CSS_Keyword("namespace");
	public @S(30) TokenList<CSS_AtNameSpaceArg> args;
	public @S(40) PunctuationSemicolon semicolon;

	public static class CSS_AtNameSpaceArg extends TokenChooser
	{
		public @CHOICE CSS_Literal literal;

		public @CHOICE static class CSS_AtNameSpaceURL extends TokenSequence
		{
			public @S(10) @OPT CSS_Identifier name;
			public @S(20) CSS_Keyword URL = new CSS_Keyword("URL");
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) CSS_FileName url;
			public @S(50) PunctuationRightParen rightParen;
		}
	}
}
