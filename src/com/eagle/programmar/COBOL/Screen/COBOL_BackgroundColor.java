// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration.COBOL_NumberOrIdentifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_BackgroundColor extends TokenSequence
{
	public @S(10) COBOL_Keyword BACKGROUND = new COBOL_Keyword("BACKGROUND-COLOR");
	public @S(20) @OPT COBOL_NumberOrIdentifier color;
}
