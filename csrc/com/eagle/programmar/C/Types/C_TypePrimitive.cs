// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.C.Types
{
	using C_Program = com.eagle.programmar.C.C_Program;
	using C_Keyword = com.eagle.programmar.C.Terminals.C_Keyword;
	using C_KeywordChoice = com.eagle.programmar.C.Terminals.C_KeywordChoice;
	using C_PunctuationChoice = com.eagle.programmar.C.Terminals.C_PunctuationChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class C_TypePrimitive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT C_Keyword CONST = new com.eagle.programmar.C.Terminals.C_Keyword("const");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT C_KeywordChoice UNSIGNED = new com.eagle.programmar.C.Terminals.C_KeywordChoice("signed", "unsigned", "__signed__");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.C.Terminals.C_KeywordChoice primitive = new com.eagle.programmar.C.Terminals.C_KeywordChoice(com.eagle.programmar.C.C_Program.getPrimitives());
		public C_KeywordChoice primitive = new C_KeywordChoice(C_Program.Primitives);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT C_Keyword INT = new com.eagle.programmar.C.Terminals.C_Keyword("int");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<C_TypeStar> stars;
		public  OPT;

		public class C_TypeStar : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.C.Terminals.C_PunctuationChoice starAmpersand = new com.eagle.programmar.C.Terminals.C_PunctuationChoice("*", "&&", "&");
			public C_PunctuationChoice starAmpersand = new C_PunctuationChoice("*", "&&", "&");
		}
	}
}
