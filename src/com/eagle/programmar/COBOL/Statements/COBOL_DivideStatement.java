// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class COBOL_DivideStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsdivi.htm") COBOL_Keyword DIVIDE = new COBOL_Keyword("DIVIDE");
	public @S(20) COBOL_Expression y;
	public @S(30) COBOL_KeywordChoice BYINTO = new COBOL_KeywordChoice("BY", "INTO");
	public @S(40) COBOL_Expression x;
	public @S(50) @OPT COBOL_DivideGiving giving;
	public @S(60) @OPT COBOL_DivideRemainder remainder;
	public @S(70) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
	
	public static class COBOL_DivideGiving extends TokenSequence
	{
		public @S(10) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
		public @S(20) COBOL_Identifier_Reference quotient;
	}
	
	public static class COBOL_DivideRemainder extends TokenSequence
	{
		public @S(10) COBOL_Keyword REMAINDER = new COBOL_Keyword("REMAINDER");
		public @S(20) COBOL_Identifier_Reference remainder;
	}
}
