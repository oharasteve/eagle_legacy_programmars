// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Modifiable_Identifier = com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
	using COBOL_HexNumber = com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class COBOL_CallStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpscall.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CALL");
		public @DOC("rlpscall.htm") COBOL_Keyword CALL = new COBOL_Keyword("CALL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_KeywordChoice how = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("STATICCOBOL", "WINAPI");
		public @OPT COBOL_KeywordChoice how = new COBOL_KeywordChoice("STATICCOBOL", "WINAPI");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) COBOL_CallWhat callWhat;
		public COBOL_CallWhat callWhat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Keyword USING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("USING");
		public @OPT COBOL_Keyword USING = new COBOL_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_CallParameter> parameters;
		public @OPT TokenList<COBOL_CallParameter> parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_CallReturning returning;
		public @OPT COBOL_CallReturning returning;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<COBOL_CallException> exceptions;
		public @OPT TokenList<COBOL_CallException> exceptions;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT COBOL_Keyword ENDCALL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-CALL");
		public @OPT COBOL_Keyword ENDCALL = new COBOL_Keyword("END-CALL");

		public static class COBOL_CallWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXcallFunction;
			public COBOL_Literal XXcallFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_HexNumber XXcallHex;
			public COBOL_HexNumber XXcallHex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Identifier_Reference XXcallVariable;
			public COBOL_Identifier_Reference XXcallVariable;
		}

		public static class COBOL_CallParameter extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public @OPT PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
			public @OPT COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_KeywordChoice byHow = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
			public @OPT COBOL_KeywordChoice byHow = new COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.COBOL_Expression expression;
			public COBOL_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_ValueSize size;
			public @OPT COBOL_ValueSize size;

			public static class COBOL_ValueSize extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SIZE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SIZE");
				public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Number size;
				public COBOL_Number size;
			}
		}

		public static class COBOL_CallReturning extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RETURNING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RETURNING");
			public COBOL_Keyword RETURNING = new COBOL_Keyword("RETURNING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier variable;
			public COBOL_Modifiable_Identifier variable;
		}

		public static class COBOL_CallException extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT COBOL_Keyword NOT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NOT");
			public @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ON = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ON");
			public COBOL_Keyword ON = new COBOL_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword EXCEPTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EXCEPTION");
			public COBOL_Keyword EXCEPTION = new COBOL_Keyword("EXCEPTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Statement> statements;
			public TokenList<COBOL_Statement> statements;
		}
	}

}
