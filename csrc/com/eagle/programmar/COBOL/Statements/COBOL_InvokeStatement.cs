// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2015

namespace com.eagle.programmar.COBOL.Statements
{
	using COBOL_AbstractStatement = com.eagle.programmar.COBOL.COBOL_AbstractStatement;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class COBOL_InvokeStatement : COBOL_AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword INVOKE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("INVOKE");
		public COBOL_Keyword INVOKE = new COBOL_Keyword("INVOKE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference object;
		public COBOL_Identifier_Reference @object;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Literal entryPoint;
		public COBOL_Literal entryPoint;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_InvokeUsing using;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT COBOL_InvokeReturning returning;
		public  OPT;

		public class COBOL_InvokeUsing : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword USING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("USING");
			public COBOL_Keyword USING = new COBOL_Keyword("USING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_InvokeBy by;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression expr;
			public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_InvokeSize size;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<COBOL_InvokeMoreUsing> more;
			public  OPT;

			public class COBOL_InvokeSize : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SIZE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SIZE");
				public COBOL_Keyword SIZE = new COBOL_Keyword("SIZE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Number size;
				public COBOL_Number size;
			}

			public class COBOL_InvokeMoreUsing : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_InvokeBy by;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.COBOL_Expression expr;
				public COBOL_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_InvokeSize size;
				public  OPT;
			}

			public class COBOL_InvokeBy : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword BY = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("BY");
				public COBOL_Keyword BY = new COBOL_Keyword("BY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice VALUE = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
				public COBOL_KeywordChoice VALUE = new COBOL_KeywordChoice("CONTENT", "REFERENCE", "VALUE");
			}
		}

		public class COBOL_InvokeReturning : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RETURNING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RETURNING");
			public COBOL_Keyword RETURNING = new COBOL_Keyword("RETURNING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference result;
			public COBOL_Identifier_Reference result;
		}
	}

}
