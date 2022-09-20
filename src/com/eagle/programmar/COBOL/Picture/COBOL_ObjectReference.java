// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_ObjectReference extends TokenSequence
{
	public @S(10) COBOL_Keyword OBJECT = new COBOL_Keyword("OBJECT");
	public @S(20) COBOL_Keyword REFERENCE = new COBOL_Keyword("REFERENCE");
	public @S(30) @OPT COBOL_Identifier_Reference id;
}
