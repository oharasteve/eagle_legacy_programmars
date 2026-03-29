// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Natural_DisplayParameter : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_DisplayParameterContents contents;
		public Natural_DisplayParameterContents contents;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public class Natural_DisplayParameterContents : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DisplayParametersAD XXparameterAD;
			public Natural_DisplayParametersAD XXparameterAD;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DisplayParametersCD XXparameterCD;
			public Natural_DisplayParametersCD XXparameterCD;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class NaturalDisplayParameterFieldRepresentation extends com.eagle.tokens.TokenSequence
			public class NaturalDisplayParameterFieldRepresentation : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword AD = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AD");
				public Natural_Keyword AD = new Natural_Keyword("AD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_DisplayParametersAD parameters;
				public Natural_DisplayParametersAD parameters;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class NaturalDisplayParameterColorDefinition extends com.eagle.tokens.TokenSequence
			public class NaturalDisplayParameterColorDefinition : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword CD = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("CD");
				public Natural_Keyword CD = new Natural_Keyword("CD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_DisplayParametersCD parameters;
				public Natural_DisplayParametersCD parameters;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class NaturalDisplayParameterPrintMode extends com.eagle.tokens.TokenSequence
			public class NaturalDisplayParameterPrintMode : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword PM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("PM");
				public Natural_Keyword PM = new Natural_Keyword("PM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Natural_DisplayParametersPM parameters;
				public Natural_DisplayParametersPM parameters;
			}
		}

		public class Natural_DisplayParametersAD : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice param = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("B", "C", "D", "I", "N", "U", "V");
			public Natural_KeywordChoice param = new Natural_KeywordChoice("B", "C", "D", "I", "N", "U", "V");
		}

		public class Natural_DisplayParametersCD : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice param = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("BL", "GR", "NE", "PI", "RE", "TU", "YE");
			public Natural_KeywordChoice param = new Natural_KeywordChoice("BL", "GR", "NE", "PI", "RE", "TU", "YE");
		}

		public class Natural_DisplayParametersPM : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice param = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("C", "D", "I", "N");
			public Natural_KeywordChoice param = new Natural_KeywordChoice("C", "D", "I", "N");
		}
	}
}
