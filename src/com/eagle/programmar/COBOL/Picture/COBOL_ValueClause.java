// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.COBOL_Picture_Value;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ValueClause extends TokenSequence
{
	public @S(10) COBOL_KeywordChoice VALUE = new COBOL_KeywordChoice("VALUE", "VALUES");
	public @S(20) @OPT COBOL_KeywordChoice ARE = new COBOL_KeywordChoice("ARE", "IS");
	public @S(30) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public @S(40) TokenList<COBOL_Picture_Value> values;
}
