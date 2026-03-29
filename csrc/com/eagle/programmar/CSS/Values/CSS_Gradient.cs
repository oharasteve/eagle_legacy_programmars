// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

namespace com.eagle.programmar.CSS.Values
{
	using CSS_Value = com.eagle.programmar.CSS.CSS_Value;
	using CSS_Color = com.eagle.programmar.CSS.Terminals.CSS_Color;
	using CSS_HexNumber = com.eagle.programmar.CSS.Terminals.CSS_HexNumber;
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_KeywordChoice = com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
	using CSS_Number = com.eagle.programmar.CSS.Terminals.CSS_Number;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class CSS_Gradient : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice GRADIENT = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("linear-gradient", "-moz-linear-gradient", "-ms-linear-gradient", "-o-linear-gradient", "-webkit-linear-gradient", "-webkit-gradient");
		public CSS_KeywordChoice GRADIENT = new CSS_KeywordChoice("linear-gradient", "-moz-linear-gradient", "-ms-linear-gradient", "-o-linear-gradient", "-webkit-linear-gradient", "-webkit-gradient");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CSS_Gradient_Piece piece;
		public CSS_Gradient_Piece piece;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CSS_MoreGradient> moreGradients;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class CSS_Gradient_Piece : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Keyword XXLINEAR = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("linear");
			public CSS_Keyword XXLINEAR = new CSS_Keyword("linear");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_KeywordChoice XXdirection = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("top", "bottom", "left", "right");
			public CSS_KeywordChoice XXdirection = new CSS_KeywordChoice("top", "bottom", "left", "right");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_HexNumber XXnumber;
			public CSS_HexNumber XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Color XXcolor;
			public CSS_Color XXcolor;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSS_Gradient_Direction extends com.eagle.tokens.TokenSequence
			public class CSS_Gradient_Direction : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice fromTo = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("from", "to");
				public CSS_KeywordChoice fromTo = new CSS_KeywordChoice("from", "to");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice direction = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("top", "bottom", "left", "right");
				public CSS_KeywordChoice direction = new CSS_KeywordChoice("top", "bottom", "left", "right");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSS_Gradient_Source extends com.eagle.tokens.TokenSequence
			public class CSS_Gradient_Source : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice fromTo = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("from", "to");
				public CSS_KeywordChoice fromTo = new CSS_KeywordChoice("from", "to");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.CSS_Value value;
				public CSS_Value value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSS_NumberNumber extends com.eagle.tokens.TokenSequence
			public class CSS_NumberNumber : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Number number1;
				public CSS_Number number1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSS_Number number2;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CSS_Punctuation percent = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('%');
				public  OPT;
			}
		}

		public class CSS_MoreGradient : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PunctuationComma comma;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSS_Gradient_Piece nextPiece;
			public CSS_Gradient_Piece nextPiece;
		}
	}

}
