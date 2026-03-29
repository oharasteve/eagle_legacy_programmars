// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2022

namespace com.eagle.programmar.C
{
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_Punctuation = com.eagle.programmar.C.Terminals.C_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class C_Declaration : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_Punctuation leftBracket1 = new com.eagle.programmar.C.Terminals.C_Punctuation("[");
		public C_Punctuation leftBracket1 = new C_Punctuation("[");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.C.Terminals.C_Punctuation leftBracket2 = new com.eagle.programmar.C.Terminals.C_Punctuation("[");
		public C_Punctuation leftBracket2 = new C_Punctuation("[");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_KeywordChoice DECLARATION = new com.eagle.programmar.C.Terminals.C_KeywordChoice("fallthrough", "__fallthrough__", "maybe_unused", "nodiscard");
		public C_KeywordChoice DECLARATION = new C_KeywordChoice("fallthrough", "__fallthrough__", "maybe_unused", "nodiscard");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.C.Terminals.C_Punctuation rightBracket1 = new com.eagle.programmar.C.Terminals.C_Punctuation("]");
		public C_Punctuation rightBracket1 = new C_Punctuation("]");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.C.Terminals.C_Punctuation rightBracket2 = new com.eagle.programmar.C.Terminals.C_Punctuation("]");
		public C_Punctuation rightBracket2 = new C_Punctuation("]");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
