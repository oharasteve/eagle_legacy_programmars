// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_RedefinesClause extends TokenSequence
{
	public @S(10) COBOL_Keyword REDEFINES = new COBOL_Keyword("REDEFINES");
	public @S(20) COBOL_Identifier_Reference id;
}