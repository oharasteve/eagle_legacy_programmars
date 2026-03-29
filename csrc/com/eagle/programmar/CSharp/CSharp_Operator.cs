// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2022

namespace com.eagle.programmar.CSharp
{
	using CSharp_MethodBody = com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
	using CSharp_MethodModifier = com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifier;
	using CSharp_MethodParameters = com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameters;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_PunctuationChoice = com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class CSharp_Operator : TokenSequence
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
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comment2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) CSharp_Type returnType;
		public CSharp_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword OPERATOR = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("operator");
		public CSharp_Keyword OPERATOR = new CSharp_Keyword("operator");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice minus = new com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice("+", "-", "<", ">", "<=", ">=", "==", "!=");
		public CSharp_PunctuationChoice minus = new CSharp_PunctuationChoice("+", "-", "<", ">", "<=", ">=", "==", "!=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT CSharp_MethodParameters parameters;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @NEWLINE CSharp_MethodBody body;
		public  NEWLINE;
	}

}
