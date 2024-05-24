// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_WriteStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpswrit.htm") COBOL_Keyword WRITE = new COBOL_Keyword("WRITE");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) @OPT COBOL_WriteFrom from;
	public @S(40) @OPT COBOL_WriteAfter after;
	public @S(50) @OPT COBOL_WriteKey key;
	public @S(60) @OPT COBOL_Keyword ENDWRITE = new COBOL_Keyword("END-WRITE");

	public static class COBOL_WriteFrom extends TokenSequence
	{
		public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public @S(20) COBOL_Expression fromWhat;
	}

	public static class COBOL_WriteAfter extends TokenSequence
	{
		public @S(10) COBOL_KeywordChoice after = new COBOL_KeywordChoice("BEFORE", "AFTER");
		public @S(20) @OPT COBOL_Keyword ADVANCING = new COBOL_Keyword("ADVANCING");
		public @S(30) @OPT COBOL_Expression lines;
		public @S(40) @OPT COBOL_KeywordChoice what = new COBOL_KeywordChoice("PAGE", "LINE", "LINES");
	}

	public static class COBOL_WriteKey extends TokenSequence
	{
		public @S(10) COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public @S(20) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public @S(30) TokenList<COBOL_Statement> statements;
	}
}
