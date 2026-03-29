// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Django.Expressions
{
	using Django_Expression = com.eagle.programmar.Django.Django_Expression;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using Django_KeywordChoice = com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
	using Django_Punctuation = com.eagle.programmar.Django.Terminals.Django_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Django_BarExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Django_Expression left = new com.eagle.programmar.Django.Django_Expression(this, AllowedPrecedence.ATLEAST);
		public Django_Expression left = new Django_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Terminals.Django_Punctuation bar = new com.eagle.programmar.Django.Terminals.Django_Punctuation("|");
		public Django_Punctuation bar = new Django_Punctuation("|");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Django_BarWhat what;
		public Django_BarWhat what;

		public class Django_BarWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_KeywordChoice XXLENGTH = new com.eagle.programmar.Django.Terminals.Django_KeywordChoice("int", "length");
			public Django_KeywordChoice XXLENGTH = new Django_KeywordChoice("int", "length");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Django_BarDefault extends com.eagle.tokens.TokenSequence
			public class Django_BarDefault : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword DEFAULT = new com.eagle.programmar.Django.Terminals.Django_Keyword("default");
				public Django_Keyword DEFAULT = new Django_Keyword("default");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Django.Django_Expression expr;
				public Django_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}
	}

}
