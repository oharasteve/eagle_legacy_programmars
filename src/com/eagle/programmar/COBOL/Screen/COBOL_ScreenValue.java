// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenSequence;

public class COBOL_ScreenValue extends TokenSequence
{
	public @S(10) COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
	public @S(20) @OPT COBOL_Literal value;
}