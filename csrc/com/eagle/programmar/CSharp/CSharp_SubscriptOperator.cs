// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.CSharp
{
	using CSharp_MethodModifier = com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifier;
	using CSharp_MethodParameter = com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class CSharp_SubscriptOperator : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NEWLINE TokenList<com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) CSharp_Type returnType;
		public CSharp_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword THIS = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("this");
		public CSharp_Keyword THIS = new CSharp_Keyword("this");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter parameter;
		public CSharp_MethodParameter parameter;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) CSharp_MethodImplementation implementation;
		public CSharp_MethodImplementation implementation;
	}

}
