// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Go.Statements
{
	using Go_Expression = com.eagle.programmar.Go.Go_Expression;
	using Go_Variable = com.eagle.programmar.Go.Go_Variable;
	using Go_EOLN = com.eagle.programmar.Go.Terminals.Go_EOLN;
	using Go_Keyword = com.eagle.programmar.Go.Terminals.Go_Keyword;
	using Go_PunctuationChoice = com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Go_ConstBlock : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#Constant_declarations") com.eagle.programmar.Go.Terminals.Go_Keyword CONST = new com.eagle.programmar.Go.Terminals.Go_Keyword("const");
		public @DOC("#Constant_declarations") Go_Keyword CONST = new Go_Keyword("const");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Terminals.Go_EOLN eoln1;
		public Go_EOLN eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Go_ConstAssignment> constLine;
		public TokenList<Go_ConstAssignment> constLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Go.Terminals.Go_EOLN eoln2;
		public Go_EOLN eoln2;

		public static class Go_ConstAssignment extends TokenSequence implements AbstractStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Go.Go_Variable, com.eagle.tokens.punctuation.PunctuationComma> vars;
			public SeparatedList<Go_Variable, PunctuationComma> vars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Terminals.Go_PunctuationChoice equals = new com.eagle.programmar.Go.Terminals.Go_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
			public Go_PunctuationChoice equals = new Go_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Go_Expression value;
			public Go_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Go.Terminals.Go_EOLN eoln;
			public Go_EOLN eoln;
		}
	}

}
