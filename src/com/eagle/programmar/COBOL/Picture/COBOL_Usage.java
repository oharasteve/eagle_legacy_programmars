// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class COBOL_Usage extends TokenSequence
{
	public @S(10) COBOL_Keyword USAGE = new COBOL_Keyword("USAGE");
	public @S(20) COBOL_KeywordChoice type = new COBOL_KeywordChoice(
			"1-RECTL",
			"2SIZE",
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
}