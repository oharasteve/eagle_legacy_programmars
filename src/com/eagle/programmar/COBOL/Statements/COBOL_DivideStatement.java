// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class COBOL_DivideStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsdivi.htm") COBOL_Keyword DIVIDE = new COBOL_Keyword("DIVIDE");
	public @S(20) COBOL_DivideType type;
	public @S(30) @OPT COBOL_DivideRemainder remainder;
	public @S(40) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");

	public static class COBOL_DivideType extends TokenChooser
	{
		public @CHOICE static class COBOL_DivideNoGivingBy extends TokenSequence
		{
			public @S(10) COBOL_Variable var;
			public @S(20) COBOL_Keyword BY = new COBOL_Keyword("BY");
			public @S(30) COBOL_Expression expr;
		}

		public @CHOICE static class COBOL_DivideNoGivingInto extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) COBOL_Keyword INTO = new COBOL_Keyword("INTO");
			public @S(30) COBOL_Variable var;
		}

		public @FIRST static class COBOL_DivideWithGiving extends TokenSequence
		{
			public @S(10) COBOL_Expression expr1;
			public @S(20) COBOL_KeywordChoice BYINTO = new COBOL_KeywordChoice("BY", "INTO");
			public @S(30) COBOL_Expression expr2;
			public @S(40) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
			public @S(50) COBOL_Variable quotient;
		}
	}

	public static class COBOL_DivideRemainder extends TokenSequence
	{
		public @S(10) COBOL_Keyword REMAINDER = new COBOL_Keyword("REMAINDER");
		public @S(20) COBOL_Variable remainder;
	}
}
