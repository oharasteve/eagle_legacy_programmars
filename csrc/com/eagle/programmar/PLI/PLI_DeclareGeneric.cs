// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

namespace com.eagle.programmar.PLI
{
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using PLI_Variable_Definition = com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class PLI_DeclareGeneric : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword DECLARE = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("DECLARE");
		public PLI_Keyword DECLARE = new PLI_Keyword("DECLARE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition id;
		public PLI_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Terminals.PLI_Keyword GENERIC = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("GENERIC");
		public PLI_Keyword GENERIC = new PLI_Keyword("GENERIC");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) PLI_GenericWhen when;
		public PLI_GenericWhen when;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<PLI_GenericMoreWhens> moreWhens;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class PLI_GenericWhen : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference id;
			public PLI_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Keyword WHEN = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("WHEN");
			public PLI_Keyword WHEN = new PLI_Keyword("WHEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT SeparatedList<PLI_Type, com.eagle.tokens.punctuation.PunctuationComma> types;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class PLI_GenericMoreWhens : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PLI_GenericWhen when;
			public PLI_GenericWhen when;
		}
	}

}
