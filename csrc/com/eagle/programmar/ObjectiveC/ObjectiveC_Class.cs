// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 10, 2022

namespace com.eagle.programmar.ObjectiveC
{
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using CPlus_Class_Definition = com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class ObjectiveC_Class : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Punctuation at = new com.eagle.programmar.C.Terminals.C_Punctuation("@");
		public C_Punctuation at = new C_Punctuation("@");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Keyword CLASS = new com.eagle.programmar.C.Terminals.C_Keyword("class");
		public C_Keyword CLASS = new C_Keyword("class");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CPlus.Symbols.CPlus_Class_Definition name;
		public CPlus_Class_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
