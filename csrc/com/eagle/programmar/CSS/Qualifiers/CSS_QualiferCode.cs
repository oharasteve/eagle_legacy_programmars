// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

namespace com.eagle.programmar.CSS.Qualifiers
{
	using CSS_KeywordChoice = com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
	using CSS_Literal = com.eagle.programmar.CSS.Terminals.CSS_Literal;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class CSS_QualiferCode : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice CODE = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("controls", "data-original-title", "disabled", "hidden", "href", "title");
		public CSS_KeywordChoice CODE = new CSS_KeywordChoice("controls", "data-original-title", "disabled", "hidden", "href", "title");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSS_BracketsHatEquals hatEquals;
		public  OPT;

		public class CSS_BracketsHatEquals : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSS.Terminals.CSS_Punctuation hat = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('^');
			public CSS_Punctuation hat = new CSS_Punctuation('^');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSS.Terminals.CSS_Literal literal;
			public CSS_Literal literal;
		}
	}
}
