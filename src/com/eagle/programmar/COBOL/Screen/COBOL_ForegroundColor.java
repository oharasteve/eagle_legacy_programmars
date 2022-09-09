// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_ForegroundColor extends TokenSequence
{
	public @S(10) COBOL_Keyword FOREGROUND = new COBOL_Keyword("FOREGROUND-COLOR");
	public @S(20) @OPT COBOL_NumberOrIdentifier color;
}