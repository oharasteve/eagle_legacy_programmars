// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.JavaP.Parameters;

import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class JavaP_OneClassFlags extends TokenSequence
{
	public @S(10) JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
	public @S(20) PunctuationColon colon;
	public @S(30) @OPT JavaP_OneFlagCode flagCode;
	public @S(40) @OPT SeparatedList<JavaP_OneClassFlag, PunctuationComma> flags;
	public @S(50) JavaP_EndOfLine eoln;

	public static class JavaP_OneFlagCode extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) JavaP_HexNumber hex;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class JavaP_OneClassFlag extends TokenChooser
	{
		public @CHOICE JavaP_KeywordChoice ACC = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
	}
}