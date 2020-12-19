// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_AddStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsadd.htm") COBOL_Keyword ADD = new COBOL_Keyword("ADD");
	public @S(20) COBOL_Expression expr;
	public @S(30) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
	public @S(40) @OPT COBOL_AddTo addTo;
	public @S(50) @OPT COBOL_AddGiving giving;
	public @S(60) @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
	public @S(70) @OPT COBOL_Keyword ENDADD = new COBOL_Keyword("END-ADD");
	
	public static class COBOL_AddMoreExprs extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) COBOL_Expression expr;
	}

	public static class COBOL_AddTo extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
		public @S(20) COBOL_Variable var;
		public @S(30) @OPT TokenList<COBOL_AddMoreVars> moreVars;
		
		public static class COBOL_AddMoreVars extends TokenSequence
		{
			public @S(10) @OPT PunctuationComma comma;
			public @S(20) COBOL_Variable var;
		}
	}
	
	public static class COBOL_AddGiving extends TokenSequence
	{
		public @S(10) @OPT @CURIOUS("ADD: Extra comma") PunctuationComma comma;
		public @S(20) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(30) TokenList<COBOL_Identifier_Reference> sum;
		public @S(40) @OPT COBOL_Subscript subscript;
	}

	public static class COBOL_AddOnSizeError extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(30) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
		public @S(40) COBOL_Keyword ERROR = new COBOL_Keyword("ERROR");
		public @S(50) TokenList<COBOL_Statement> actions;
	}
}
