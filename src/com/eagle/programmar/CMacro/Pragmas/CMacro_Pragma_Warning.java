// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Warning extends TokenSequence
{
	public @S(10) CMacro_Keyword WARNING = new CMacro_Keyword("warning");
	public @S(20) @OPT PunctuationLeftParen leftParen;
	public @S(30) CMacro_KeywordChoice DISABLE = new CMacro_KeywordChoice("disable", "restore", "push", "pop",
			"default");
	public @S(40) @OPT PunctuationColon colon;
	public @S(50) @OPT TokenList<CMacro_PragmaCode> codes;
	public @S(60) @OPT PunctuationRightParen rightParen;

	public static class CMacro_PragmaCode extends TokenChooser
	{
		public @CHOICE PunctuationComma comma;
		public @CHOICE CMacro_Number number; // 1718 1501 0612 3021 4702 etc etc
		public @CHOICE CMacro_Identifier code; // CS0618 and CS1718
	}
}
