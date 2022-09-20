// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_DeleteStatement extends COBOL_AbstractStatement
{
	public @S(10) COBOL_Keyword DELETE = new COBOL_Keyword("DELETE");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) @OPT COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
	public @S(40) @OPT TokenList<COBOL_DeleteInvalidKey> invalidKeys;
	public @S(50) @OPT COBOL_Keyword ENDDELETE = new COBOL_Keyword("END-DELETE");
	
	public static class COBOL_DeleteInvalidKey extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword INVALID  = new COBOL_Keyword("INVALID");
		public @S(30) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public @S(40) TokenList<COBOL_Statement> statements;
	}
}
