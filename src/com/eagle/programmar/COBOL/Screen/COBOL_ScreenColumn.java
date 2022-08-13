// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenSequence;

public class COBOL_ScreenColumn extends TokenSequence
{
	public @S(10) COBOL_KeywordChoice COLUMN = new COBOL_KeywordChoice("COLUMN", "COL");
	public @S(20) @OPT COBOL_Number number;
}
