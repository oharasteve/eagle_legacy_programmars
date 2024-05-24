// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReleaseStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rele.htm") COBOL_Keyword RELEASE = new COBOL_Keyword("RELEASE");
	public @S(20) COBOL_Identifier_Reference id1;
	public @S(30) @OPT COBOL_ReleaseFrom from;

	public static class COBOL_ReleaseFrom extends TokenSequence
	{
		public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public @S(20) COBOL_Identifier_Reference id2;
	}
}
