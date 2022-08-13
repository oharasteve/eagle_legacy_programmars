// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_Sign extends TokenSequence
{
	public @S(10) COBOL_Keyword SIGN = new COBOL_Keyword("SIGN");
	public @S(20) COBOL_Keyword TRAILING = new COBOL_Keyword("TRAILING");
	public @S(30) COBOL_Keyword SEPARATE = new COBOL_Keyword("SEPARATE");
}