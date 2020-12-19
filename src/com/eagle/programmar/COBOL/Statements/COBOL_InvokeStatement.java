// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_InvokeStatement extends COBOL_AbstractStatement
{
	public @S(10) COBOL_Keyword INVOKE = new COBOL_Keyword("INVOKE");
	public @S(20) COBOL_Identifier_Reference object;
	public @S(30) COBOL_Literal entryPoint;
	public @S(40) @OPT COBOL_InvokeUsing using;
	public @S(50) @OPT COBOL_InvokeReturning returning;
	
	public static class COBOL_InvokeUsing extends TokenSequence
	{
		public @S(10) COBOL_Keyword USING = new COBOL_Keyword("USING");
		public @S(20) @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @S(30) @OPT COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
		public @S(40) COBOL_Expression expr;
		public @S(50) @OPT COBOL_InvokeSize size;
		public @S(60) @OPT TokenList<COBOL_InvokeMoreUsing> more;
		
		public static class COBOL_InvokeSize extends TokenSequence
		{
			public @S(10) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
			public @S(20) COBOL_Number size;
		}
		
		public static class COBOL_InvokeMoreUsing extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) COBOL_Expression expr;
			public @S(30) @OPT COBOL_InvokeSize size;
		}
	}
	
	public static class COBOL_InvokeReturning extends TokenSequence
	{
		public @S(10) COBOL_Keyword RETURNING = new COBOL_Keyword("RETURNING");
		public @S(20) COBOL_Identifier_Reference result;
	}
}
