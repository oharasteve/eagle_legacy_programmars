// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

namespace com.eagle.programmar.Django
{
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using Django_KeywordChoice = com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
	using Django_Literal = com.eagle.programmar.Django.Terminals.Django_Literal;
	using Django_Number = com.eagle.programmar.Django.Terminals.Django_Number;
	using Django_Punctuation = com.eagle.programmar.Django.Terminals.Django_Punctuation;
	using Django_PunctuationChoice = com.eagle.programmar.Django.Terminals.Django_PunctuationChoice;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Django_Insert : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startBraceBrace = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("{{");
		public HTML_Punctuation startBraceBrace = new HTML_Punctuation("{{");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Django_PunctuationChoice plus = new com.eagle.programmar.Django.Terminals.Django_PunctuationChoice("+", "-", "/");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Django_InsertWhat what;
		public Django_InsertWhat what;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.HTML.Terminals.HTML_Punctuation endBraceBrace = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("}}");
		public HTML_Punctuation endBraceBrace = new HTML_Punctuation("}}");

		public class Django_InsertWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Django_InsertSuper extends com.eagle.tokens.TokenSequence
			public class Django_InsertSuper : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword SUPER = new com.eagle.programmar.Django.Terminals.Django_Keyword("super");
				public Django_Keyword SUPER = new Django_Keyword("super");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Django_InsertSuperArgs args;
				public  OPT;

				public class Django_InsertSuperArgs : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Django_InsertExpression extends com.eagle.tokens.TokenSequence
			public class Django_InsertExpression : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Django_Expression expr;
				public Django_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Django_InsertDot insertDot;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Django_OrWhat what;
				public  OPT;
			}
		}

		public class Django_InsertDot : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Django_InsertDotWhat what;
			public Django_InsertDotWhat what;

			public class Django_InsertDotWhat : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_Number XXnumber;
				public Django_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Django_Variable XXvariable;
				public Django_Variable XXvariable;
			}
		}

		public class Django_OrWhat : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Punctuation verticalBar = new com.eagle.programmar.Django.Terminals.Django_Punctuation('|');
			public Django_Punctuation verticalBar = new Django_Punctuation('|');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Terminals.Django_KeywordChoice DATE = new com.eagle.programmar.Django.Terminals.Django_KeywordChoice("capfirst", "date", "escape", "safe");
			public Django_KeywordChoice DATE = new Django_KeywordChoice("capfirst", "date", "escape", "safe");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationColon colon;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Django_Literal literal;
			public  OPT;
		}
	}

}
