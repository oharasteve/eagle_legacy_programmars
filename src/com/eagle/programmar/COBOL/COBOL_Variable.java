// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 27, 2013

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_Variable extends TokenSequence
{
	public @S(10) COBOL_Modifiable_Identifier id;
	public @S(20) @OPT TokenList<COBOL_Subscript> subscript;
	public @S(30) @OPT TokenList<COBOL_OfVariable> ofList;

	public static class COBOL_OfVariable extends TokenSequence
	{
		public @S(10) COBOL_Keyword OF = new COBOL_Keyword("OF");
		public @S(20) COBOL_Identifier_Reference id;
	}
}
