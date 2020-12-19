// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_SubtractStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpssubt.htm") COBOL_Keyword SUBTRACT = new COBOL_Keyword("SUBTRACT");
	public @S(20) COBOL_Expression expr;
	public @S(30) COBOL_SubtractFrom from;
	public @S(40) @OPT COBOL_SubtractGiving giving;
	
	public static class COBOL_SubtractGiving extends TokenSequence
	{
		public @S(10) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(20) COBOL_Variable result;
		public @S(30) @OPT COBOL_Subscript subscript;
		public @S(40) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
	}
	
	public static class COBOL_SubtractFrom extends TokenSequence
	{
		public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
		public @S(20) COBOL_Expression expr;
		public @S(30) @OPT TokenList<COBOL_SubtractMoreVars> moreVars;
	}
	
	public static class COBOL_SubtractMoreVars extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Variable var;
	}
}
