// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 19, 2022

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_Type extends TokenChooser
{
	public @CHOICE COBOL_KeywordChoice type = new COBOL_KeywordChoice(
			"1-RECTL",
			"2SIZE",
			"COMP",
			"DATA-POINTER",
			"LONG",
			"POINT",
			"TAGMSG",
			"TAGPOINT",
			"TAGRECT",
			"TAGSIZE",
			"UINT",
			"ULONG"
	);

	public @CHOICE static class COBOL_TypeObject extends TokenSequence
	{
		public @S(10) COBOL_Keyword OBJECT = new COBOL_Keyword("OBJECT");
		public @S(20) COBOL_Keyword REFERENCE = new COBOL_Keyword("REFERENCE");
	}
}
