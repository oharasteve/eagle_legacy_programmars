// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReturnStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsretu.htm") COBOL_Keyword RETURN = new COBOL_Keyword("RETURN");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) COBOL_ReturnAtEndAction atEnd;
	public @S(40) COBOL_Keyword ENDRETURN = new COBOL_Keyword("END-RETURN");

	public static class COBOL_ReturnAtEndAction extends TokenSequence
	{
		public @S(10) COBOL_Keyword AT = new COBOL_Keyword("AT");
		public @S(20) COBOL_Keyword END = new COBOL_Keyword("END");
		public @S(30) TokenList<COBOL_Statement> endAction;
	}
}
