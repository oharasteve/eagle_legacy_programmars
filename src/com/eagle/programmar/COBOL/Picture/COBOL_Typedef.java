// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_Typedef extends TokenSequence
{
	public @S(10) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(20) COBOL_Keyword TYPEDEF = new COBOL_Keyword("TYPEDEF");
}