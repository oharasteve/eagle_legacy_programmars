// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Message extends TokenSequence
{
	public @S(10) CMacro_Keyword MESSAGE = new CMacro_Keyword("message");
	public @S(20) @OPT CMacro_Keyword DISABLE = new CMacro_Keyword("disable");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<CMacro_PragmaMsg, PunctuationComma> codes;
	public @S(50) PunctuationRightParen rightParen;

	public static class CMacro_PragmaMsg extends TokenChooser
	{
		public @CHOICE CMacro_Literal XXliteral;
		public @CHOICE CMacro_KeywordChoice XXUNDERFLOW = new CMacro_KeywordChoice(
				"UNDERFLOW", "FLOATOVERFL", "nosimpint");
	}
}
