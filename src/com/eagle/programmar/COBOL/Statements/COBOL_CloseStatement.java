// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_CloseStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsclos.htm") COBOL_Keyword CLOSE = new COBOL_Keyword("CLOSE");
	public @S(20) @OPT COBOL_Keyword RECOVER = new COBOL_Keyword("RECOVER");
	public @S(30) @OPT TokenList<COBOL_CloseFileList> fileList;

	public static class COBOL_CloseFileList extends TokenSequence
	{
		public @S(10) @OPT COBOL_Comment comment;
		public @S(20) @OPT PunctuationComma comma;
		public @S(30) COBOL_Identifier_Reference file;
	}
}
