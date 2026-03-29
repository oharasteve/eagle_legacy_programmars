// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 24, 2015

namespace com.eagle.programmar.CPlus
{
	using C_Expression = com.eagle.programmar.C.C_Expression;
	using C_Generic = com.eagle.programmar.C.C_Generic;
	using C_Type = com.eagle.programmar.C.C_Type;
	using C_Identifier_Reference = com.eagle.programmar.C.Symbols.C_Identifier_Reference;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class CPlus_Using : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword USING = new com.eagle.programmar.C.Terminals.C_Keyword("using");
		public C_Keyword USING = new C_Keyword("using");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Keyword NAMESPACE = new com.eagle.programmar.C.Terminals.C_Keyword("namespace");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CPlus_UsingWhat what;
		public CPlus_UsingWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class CPlus_UsingWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CPlus_UsingType1 XXtype1;
			public CPlus_UsingType1 XXtype1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CPlus_UsingType2 XXtype2;
			public CPlus_UsingType2 XXtype2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE C_Expression XXexpr;
			public C_Expression XXexpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CPlus_UsingColons XXcolons;
			public CPlus_UsingColons XXcolons;
		}

		public class CPlus_UsingType1 : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Keyword TYPE = new com.eagle.programmar.C.Terminals.C_Keyword("type");
			public C_Keyword TYPE = new C_Keyword("type");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CPlus_UsingColons type;
			public CPlus_UsingColons type;
		}

		public class CPlus_UsingType2 : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.C_Type type1;
			public C_Type type1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CPlus_UsingColons type2;
			public CPlus_UsingColons type2;
		}

		public class CPlus_UsingColons : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CPlus_UsingColon> idColons;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.C_Type id;
			public C_Type id;

			public class CPlus_UsingColon : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Symbols.C_Identifier_Reference id;
				public C_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_Generic generic;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_Punctuation colonColon = new com.eagle.programmar.C.Terminals.C_Punctuation("::");
				public C_Punctuation colonColon = new C_Punctuation("::");
			}
		}
	}

}
