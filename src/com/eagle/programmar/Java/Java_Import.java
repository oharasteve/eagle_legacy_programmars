// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Java_Import extends TokenSequence
{
	public @S(10) @NEWLINE Java_Keyword IMPORT = new Java_Keyword("import");
	public @S(20) @OPT Java_Keyword STATIC = new Java_Keyword("static");
	public @S(30) Java_Identifier id;
	public @S(40) @OPT TokenList<Java_DotIdentifierStar> dotId;
	public @S(50) @NOSPACE PunctuationSemicolon semicolon;

	public static class Java_DotIdentifierStar extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE Java_IdentifierOrStar idStar;

		public static class Java_IdentifierOrStar extends TokenChooser
		{
			public @CHOICE @NOSPACE Java_Identifier XXid;
			public @CHOICE @NOSPACE PunctuationStar XXstar;
		}
	}
}
