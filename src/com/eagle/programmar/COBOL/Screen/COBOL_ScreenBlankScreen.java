// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class COBOL_ScreenBlankScreen extends TokenSequence
{
	public @S(10) COBOL_Keyword BLANK = new COBOL_Keyword("BLANK");
	public @S(20) COBOL_KeywordChoice blank = new COBOL_KeywordChoice("SCREEN", "LINE");
}
