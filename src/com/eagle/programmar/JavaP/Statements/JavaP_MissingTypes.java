// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class JavaP_MissingTypes extends TokenSequence
{
	public @S(10) JavaP_Keyword MISSINGTYPES = new JavaP_Keyword("MissingTypes");
	public @S(20) PunctuationColon colon;
	public @S(30) JavaP_Identifier id;
	public @S(40) PunctuationEquals equals;
	public @S(50) JavaP_HexNumber number;
	public @S(60) @OPT JavaP_MissingReason reason;
	public @S(70) JavaP_EndOfLine eoln;

	public static class JavaP_MissingReason extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) JavaP_Keyword UNKNOWN = new JavaP_Keyword("unknown");
		public @S(30) JavaP_Keyword ATTRIBUTE = new JavaP_Keyword("attribute");
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) @OPT JavaP_EndOfLine eoln;
		public @S(60) @OPT TokenList<JavaP_HexNoPrefix> numbers;
	}
}
