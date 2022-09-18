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
	public @S(30) COBOL_Keyword DELIMITED = new COBOL_Keyword("DELIMITED");
	public @S(40) COBOL_Keyword BY = new COBOL_Keyword("BY");
	public @S(50) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public @S(60) COBOL_UnstringOrWhat delim;
	public @S(70) @OPT COBOL_UnstringOrClause orClause;
	public @S(80) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
	public @S(90) TokenList<COBOL_UnstringPiece> pieces;
	public @S(100) @OPT COBOL_UnstringWith with;
	public @S(110) @OPT COBOL_Overflow overflow;
	public @S(120) @OPT COBOL_Keyword ENDUNSTRING = new COBOL_Keyword("END-UNSTRING");
	
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
		
		public static class COBOL_UnstringCount extends TokenSequence
		{
			public @S(10) COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
			public @S(20) COBOL_Keyword IN = new COBOL_Keyword("IN");
			public @S(30) COBOL_Identifier_Reference countVar;
		}
	}
	
	public static class COBOL_UnstringWith extends TokenSequence
	{
		public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
		public @S(30) COBOL_Identifier_Reference withPointer;
	}
}
