// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

namespace com.eagle.programmar.CSS.Qualifiers
{
	using CSS_Keyword = com.eagle.programmar.CSS.Terminals.CSS_Keyword;
	using CSS_KeywordChoice = com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
	using CSS_Literal = com.eagle.programmar.CSS.Terminals.CSS_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class CSS_QualifierType : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Keyword TYPE = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("type");
		public CSS_Keyword TYPE = new CSS_Keyword("type");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CSS_QualifierWhichType which;
		public CSS_QualifierWhichType which;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("What does this mean>") com.eagle.programmar.CSS.Terminals.CSS_Keyword I = new com.eagle.programmar.CSS.Terminals.CSS_Keyword("i");
		public  OPT;

		public class CSS_QualifierWhichType : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_KeywordChoice XXvalue = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("button", "checkbox", "date", "datetime-local", "email", "file", "month", "number", "password", "radio", "range", "reset", "search", "submit", "time", "text");
			public CSS_KeywordChoice XXvalue = new CSS_KeywordChoice("button", "checkbox", "date", "datetime-local", "email", "file", "month", "number", "password", "radio", "range", "reset", "search", "submit", "time", "text");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Literal XXliteral;
			public CSS_Literal XXliteral;
		}
	}

}
