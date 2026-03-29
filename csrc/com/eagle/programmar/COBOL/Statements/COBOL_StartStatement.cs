// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2010

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_PunctuationChoice = com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_StartStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("rlpsstar.htm") com.eagle.programmar.COBOL.Terminals.COBOL_Keyword START = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("START");
		public @DOC("rlpsstar.htm") COBOL_Keyword START = new COBOL_Keyword("START");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference file;
		public COBOL_Identifier_Reference file;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword KEY1 = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
		public COBOL_Keyword KEY1 = new COBOL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) COBOL_StartRelOp oper;
		public COBOL_StartRelOp oper;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference value;
		public COBOL_Identifier_Reference value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_StartInvalid invalid;
		public @OPT COBOL_StartInvalid invalid;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT COBOL_Keyword ENDSTART = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("END-START");
		public @OPT COBOL_Keyword ENDSTART = new COBOL_Keyword("END-START");

		public static class COBOL_StartRelOp extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_PunctuationChoice XXoperator = new com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice("<=", ">=", "<", ">");
			public COBOL_PunctuationChoice XXoperator = new COBOL_PunctuationChoice("<=", ">=", "<", ">");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_StartOper2 extends com.eagle.tokens.TokenSequence
			public static class COBOL_StartOper2 extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GREATER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GREATER");
				public COBOL_Keyword GREATER = new COBOL_Keyword("GREATER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword THAN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("THAN");
				public COBOL_Keyword THAN = new COBOL_Keyword("THAN");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_StartOper3 extends com.eagle.tokens.TokenSequence
			public static class COBOL_StartOper3 extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword EQUAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("EQUAL");
				public COBOL_Keyword EQUAL = new COBOL_Keyword("EQUAL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TO = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TO");
				public COBOL_Keyword TO = new COBOL_Keyword("TO");
			}
		}

		public static class COBOL_StartInvalid extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INVALID = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INVALID");
			public COBOL_Keyword INVALID = new COBOL_Keyword("INVALID");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword KEY2 = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("KEY");
			public COBOL_Keyword KEY2 = new COBOL_Keyword("KEY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.COBOL_Statement> statements;
			public TokenList<COBOL_Statement> statements;
		}
	}

}
