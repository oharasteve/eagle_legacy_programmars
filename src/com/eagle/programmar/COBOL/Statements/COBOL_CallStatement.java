// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class COBOL_CallStatement extends COBOL_AbstractStatement
{
	public @S(10) @DOC("rlpscall.htm") COBOL_Keyword CALL = new COBOL_Keyword("CALL");
	public @S(20) @OPT COBOL_KeywordChoice how = new COBOL_KeywordChoice("STATICCOBOL", "WINAPI");
	public @S(30) COBOL_CallWhat callWhat;
	public @S(40) @OPT COBOL_Keyword USING = new COBOL_Keyword("USING");
	public @S(50) @OPT TokenList<COBOL_CallParameter> parameters;
	public @S(60) @OPT COBOL_CallReturning returning;
	public @S(70) @OPT TokenList<COBOL_CallException> exceptions;
	public @S(80) @OPT COBOL_Keyword ENDCALL = new COBOL_Keyword("END-CALL");

	public static class COBOL_CallWhat extends TokenChooser
	{
		public @CHOICE COBOL_Literal XXcallFunction;
		public @CHOICE COBOL_HexNumber XXcallHex;
		public @CHOICE COBOL_Identifier_Reference XXcallVariable;
	}

	public static class COBOL_CallParameter extends TokenSequence
	{
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
		public @S(30) @OPT COBOL_KeywordChoice byHow = new COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
		public @S(40) COBOL_Expression expression;
		public @S(50) @OPT COBOL_ValueSize size;

		public static class COBOL_ValueSize extends TokenSequence
		{
			public @S(10) COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
			public @S(20) COBOL_Number size;
		}
	}

	public static class COBOL_CallReturning extends TokenSequence
	{
		public @S(10) COBOL_Keyword RETURNING = new COBOL_Keyword("RETURNING");
		public @S(20) COBOL_Modifiable_Identifier variable;
	}

	public static class COBOL_CallException extends TokenSequence
	{
		public @S(10) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
		public @S(20) COBOL_Keyword ON = new COBOL_Keyword("ON");
		public @S(30) COBOL_Keyword EXCEPTION = new COBOL_Keyword("EXCEPTION");
		public @S(40) TokenList<COBOL_Statement> statements;
	}
}
