// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Overflow;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_UnstringStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsunst.htm") COBOL_Keyword UNSTRING = new COBOL_Keyword("UNSTRING");
	public @S(20) COBOL_Expression expr;
	public @S(30) @OPT COBOL_UnstringDelimited delimited;
	public @S(40) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
	public @S(50) TokenList<COBOL_UnstringPiece> pieces;
	public @S(60) @OPT COBOL_UnstringWith with;
	public @S(70) @OPT COBOL_Overflow overflow;
	public @S(80) @OPT COBOL_Keyword ENDUNSTRING = new COBOL_Keyword("END-UNSTRING");
	
	public static class COBOL_UnstringDelimited extends TokenSequence
	{
		public @S(10) COBOL_Keyword DELIMITED = new COBOL_Keyword("DELIMITED");
		public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @S(30) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
		public @S(40) COBOL_UnstringOrWhat delim;
		public @S(50) @OPT TokenList<COBOL_UnstringOrClause> orClauses;
	}
	
	public static class COBOL_UnstringOrClause extends TokenSequence
	{
		public @S(10) COBOL_Keyword OR = new COBOL_Keyword("OR");
		public @S(20) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
		public @S(30) COBOL_UnstringOrWhat unstringWhat;
	}

	public static class COBOL_UnstringOrWhat extends TokenChooser
	{
		public @CHOICE COBOL_Literal delim;
		public @CHOICE COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
	}

	public static class COBOL_UnstringPiece extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Identifier_Reference intoVar;
		public @S(30) @OPT COBOL_UnstringCount count;
		public @S(40) @OPT COBOL_UnstringDelimiter delimiter;
		
		public static class COBOL_UnstringCount extends TokenSequence
		{
			public @S(10) COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
			public @S(20) COBOL_Keyword IN = new COBOL_Keyword("IN");
			public @S(30) COBOL_Identifier_Reference countVar;
		}
		
		public static class COBOL_UnstringDelimiter extends TokenSequence
		{
			public @S(10) COBOL_Keyword DELIMITER = new COBOL_Keyword("DELIMITER");
			public @S(20) COBOL_Keyword IN = new COBOL_Keyword("IN");
			public @S(30) COBOL_Expression range;
		}
	}
	
	public static class COBOL_UnstringWith extends TokenSequence
	{
		public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
		public @S(30) COBOL_Identifier_Reference withPointer;
	}
}
