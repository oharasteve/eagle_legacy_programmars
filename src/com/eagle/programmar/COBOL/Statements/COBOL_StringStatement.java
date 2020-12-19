// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_StringStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsstri.htm") COBOL_Keyword STRING = new COBOL_Keyword("STRING");
	public @S(20) TokenList<COBOL_StringWhat> elements;
	public @S(30) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
	public @S(40) TokenList<COBOL_StringPiece> pieces;
	public @S(50) @OPT COBOL_StringWith with;
	public @S(60) @OPT COBOL_Keyword ENDSTRING = new COBOL_Keyword("END-STRING");
	
	public static class COBOL_StringWhat extends TokenSequence
	{
		public @S(10) COBOL_Expression expr;
		public @S(20) COBOL_Keyword DELIMITED1 = new COBOL_Keyword("DELIMITED");
		public @S(30) @OPT COBOL_Keyword BY1 = new COBOL_Keyword("BY");
		public @S(40) @OPT COBOL_HexOrLiteral delim;
		public @S(50) @OPT COBOL_Keyword OR = new COBOL_Keyword("OR");
		public @S(60) @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
		public @S(70) @OPT COBOL_Keyword SPACES = new COBOL_Keyword("SPACES");
		public @S(80) @OPT COBOL_Keyword SPACE = new COBOL_Keyword("SPACE");
		public @S(90) @OPT COBOL_Keyword DELIMITED2 = new COBOL_Keyword("DELIMITED");
		public @S(100) @OPT COBOL_Keyword BY2 = new COBOL_Keyword("BY");
		public @S(110) @OPT COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		
		public static class COBOL_HexOrLiteral extends TokenChooser
		{
			public @CHOICE COBOL_HexNumber hex;
			public @CHOICE COBOL_Literal literal;
		}
	}

	public static class COBOL_StringPiece extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Identifier_Reference intoVar;
		public @S(30) @OPT COBOL_StringCount count;
		
		public static class COBOL_StringCount extends TokenSequence
		{
			public @S(10) COBOL_Keyword COUNT = new COBOL_Keyword("COUNT");
			public @S(20) COBOL_Keyword IN = new COBOL_Keyword("IN");
			public @S(30) COBOL_Identifier_Reference countVar;
		}
	}
	
	public static class COBOL_StringWith extends TokenSequence
	{
		public @S(10) COBOL_Keyword WITH = new COBOL_Keyword("WITH");
		public @S(20) COBOL_Keyword POINTER = new COBOL_Keyword("POINTER");
		public @S(30) COBOL_Identifier_Reference withPointer;
	}
}
