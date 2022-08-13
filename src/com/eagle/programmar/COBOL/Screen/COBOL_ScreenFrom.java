// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Screen;

import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_ScreenFrom extends TokenSequence
{
	public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
	public @S(20) COBOL_Identifier_Reference dataRef;
	public @S(30) @OPT COBOL_Subscript subscript;
}