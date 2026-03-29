// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 2, 2022

namespace com.eagle.programmar.CPlus
{
	using C_DataInitialValue = com.eagle.programmar.C.C_DataInitialValue;
	using C_DataModifiers = com.eagle.programmar.C.C_DataModifiers;
	using C_Type = com.eagle.programmar.C.C_Type;
	using C_Variable_Definition = com.eagle.programmar.C.Symbols.C_Variable_Definition;
	using CPlus_NamespaceList = com.eagle.programmar.CPlus.CPlus_Namespace.CPlus_NamespaceList;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class CPlus_Data : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.C.C_DataModifiers> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.C_Type ctype;
		public C_Type ctype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<CPlus_Datum, com.eagle.tokens.punctuation.PunctuationComma> data;
		public SeparatedList<CPlus_Datum, PunctuationComma> data;

		public class CPlus_Datum : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CPlus_NamespaceList namespaces;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Symbols.C_Variable_Definition id;
			public C_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT C_DataInitialValue initialValue;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
		}
	}

}
