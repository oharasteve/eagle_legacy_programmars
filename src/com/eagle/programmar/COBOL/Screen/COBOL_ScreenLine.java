// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenSequence;

public class COBOL_ScreenLine extends TokenSequence
{
	public @S(10) COBOL_Keyword LINE = new COBOL_Keyword("LINE");
	public @S(20) @OPT COBOL_Punctuation plus = new COBOL_Punctuation('+');
	public @S(30) COBOL_NumberOrIdentifier number;
}