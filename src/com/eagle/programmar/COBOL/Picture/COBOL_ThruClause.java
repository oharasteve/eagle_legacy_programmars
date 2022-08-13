// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.COBOL_Picture_Value;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ThruClause extends TokenSequence
{
	public @S(10) COBOL_Keyword THRU = new COBOL_Keyword("THRU");
	public @S(20) TokenList<COBOL_Picture_Value> values;
}
