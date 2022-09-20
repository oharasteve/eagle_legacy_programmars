// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_ReadStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsread.htm") COBOL_Keyword READ = new COBOL_Keyword("READ");
	public @S(20) TokenList<COBOL_Identifier_Reference> files;
	public @S(30) TokenList<COBOL_ReadClause> entries;
	public @S(40) @OPT COBOL_Keyword ENDREAD = new COBOL_Keyword("END-READ");
	
	public static class COBOL_ReadClause extends TokenChooser
	{
		public @CHOICE COBOL_ReadInto into;
		public @CHOICE COBOL_ReadNext next;
		public @CHOICE COBOL_ReadAtEndAction atEnd;
		public @CHOICE COBOL_ReadIgnoreLock ignoreLock;
		public @CHOICE COBOL_ReadKey key;
		public @CHOICE COBOL_ReadInvalidKey invalid;
	}

	public static class COBOL_ReadInto extends TokenSequence
	{
		public @S(10) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
		public @S(20) COBOL_Identifier_Reference var;
	}
	
	public static class COBOL_ReadNext extends TokenSequence
	{
		public @S(10) COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
		public @S(20) @OPT COBOL_Keyword RECORD = new COBOL_Keyword("RECORD");
	}
	
	public static class COBOL_ReadKey extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public @S(30) COBOL_Keyword IS = new COBOL_Keyword("IS");
		public @S(40) COBOL_Identifier_Reference value;
	}

	public static class COBOL_ReadInvalidKey extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public @S(30) COBOL_Keyword KEY = new COBOL_Keyword("KEY");
		public @S(40) TokenList<COBOL_Statement> statements;
	}

	public static class COBOL_ReadAtEndAction extends TokenSequence
	{
		public @S(10) COBOL_Keyword AT = new COBOL_Keyword("AT");
		public @S(20) COBOL_Keyword END = new COBOL_Keyword("END");
		public @S(30) TokenList<COBOL_Statement> endAction;
	}
	
	public static class COBOL_ReadIgnoreLock extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) @OPT COBOL_KeywordChoice IGNORE = new COBOL_KeywordChoice("IGNORE", "KEPT");
		public @S(30) COBOL_Keyword LOCK = new COBOL_Keyword("LOCK");
	}
}
