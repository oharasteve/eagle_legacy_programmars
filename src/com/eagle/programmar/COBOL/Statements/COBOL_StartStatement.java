// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_StartStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpsstar.htm") COBOL_Keyword START = new COBOL_Keyword("START");
	public @S(20) COBOL_Identifier_Reference file;
	public @S(30) COBOL_Keyword KEY1 = new COBOL_Keyword("KEY");
	public @S(40) COBOL_StartRelOp oper;
	public @S(50) COBOL_Identifier_Reference value;
	public @S(60) @OPT COBOL_StartInvalid invalid;
	public @S(70) @OPT COBOL_Keyword ENDSTART = new COBOL_Keyword("END-START");

	public static class COBOL_StartRelOp extends TokenChooser
	{
		public @CHOICE COBOL_PunctuationChoice operator = new COBOL_PunctuationChoice("<=", ">=", "<", ">");

		public @CHOICE static class COBOL_StartOper2 extends TokenSequence
		{
			public @S(10) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(20) COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
			public @S(30) COBOL_Keyword THAN = new COBOL_Keyword("THAN");
		}

		public @CHOICE static class COBOL_StartOper3 extends TokenSequence
		{
			public @S(10) COBOL_Keyword IS = new COBOL_Keyword("IS");
			public @S(20) COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
			public @S(30) COBOL_Keyword TO = new COBOL_Keyword("TO");
		}
	}

	public static class COBOL_StartInvalid extends TokenSequence
	{
		public @S(10) COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
		public @S(20) COBOL_Keyword KEY2 = new COBOL_Keyword("KEY");
		public @S(30) TokenList<COBOL_Statement> statements;
	}
}
