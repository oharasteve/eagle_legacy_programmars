// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 20, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_RenamesClause extends TokenSequence
{
	public @S(10) COBOL_Keyword RENAMES = new COBOL_Keyword("RENAMES");
	public @S(20) COBOL_Identifier_Reference id1;
	public @S(30) @OPT COBOL_Keyword THRU = new COBOL_Keyword("THRU");
	public @S(40) @OPT COBOL_Identifier_Reference id2;
}