// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_OpenStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsopen.htm") COBOL_Keyword OPEN = new COBOL_Keyword("OPEN");
	public @S(20) COBOL_KeywordChoice inputOutput = new COBOL_KeywordChoice("I-O", "INPUT", "OUTPUT", "EXTEND");
	public @S(30) COBOL_Identifier_Reference file;
	public @S(40) @OPT TokenList<COBOL_OpenFileList> moreFiles;
	public @S(50) @OPT COBOL_Keyword LOCK = new COBOL_Keyword("LOCK");

	public static class COBOL_OpenFileList extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Identifier_Reference file;
	}
}
