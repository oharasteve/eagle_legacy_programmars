// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.COBOL_Variable;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_AddStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsadd.htm") COBOL_Keyword ADD = new COBOL_Keyword("ADD");
	public @S(20) COBOL_AddType type;
	public @S(30) @OPT TokenList<COBOL_AddOnSizeError> onErrorList;
	public @S(40) @OPT COBOL_Keyword ENDADD = new COBOL_Keyword("END-ADD");
	
	public static class COBOL_AddType extends TokenChooser
	{
		public @CHOICE static class COBOL_AddNoGiving extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
			public @S(30) @OPT COBOL_AddTo addTo;

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
		}
		
		public @FIRST static class COBOL_AddWithGiving extends TokenSequence
		{
			public @S(10) COBOL_Expression expr;
			public @S(20) @OPT TokenList<COBOL_AddMoreExprs> moreExprs;
			public @S(30) @OPT COBOL_Keyword TO = new COBOL_Keyword("TO");
			public @S(40) @OPT COBOL_Expression toExpr;
			public @S(50) @OPT @CURIOUS("Extra comma") PunctuationComma comma;
			public @S(60) COBOL_Keyword GIVING = new COBOL_Keyword("GIVING");
			public @S(70) TokenList<COBOL_Variable> vars;
		}
	}
	
	public static class COBOL_AddMoreExprs extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) COBOL_Expression expr;
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
