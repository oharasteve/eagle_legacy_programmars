// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.COBOL.Picture;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_Usage extends TokenSequence
{
	public @S(10) @OPT COBOL_Keyword USAGE = new COBOL_Keyword("USAGE");
	public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(30) COBOL_UsageType usage;
	
	public static class COBOL_UsageType extends TokenChooser
	{
		public @CHOICE COBOL_KeywordChoice XXtype = new COBOL_KeywordChoice(
				"BINARY", "COMP", "COMP-0", "COMP-1", "COMP-3", "COMP-5",
				"COMP-X", "COMPUTATIONAL", "DISPLAY", "PACKED-DECIMAL");
		
		public @CHOICE static class COBOL_UsageObject extends TokenSequence
		{
			public @S(10) COBOL_Keyword OBJECT = new COBOL_Keyword("OBJECT");
			public @S(20) COBOL_Keyword REFERENCE = new COBOL_Keyword("REFERENCE");
			public @S(30) @OPT COBOL_Identifier_Reference className;
		}
	}
}