// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

namespace com.eagle.programmar.CSS
{
	using CSS_QualiferCode = com.eagle.programmar.CSS.Qualifiers.CSS_QualiferCode;
	using CSS_QualifierClass = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierClass;
	using CSS_QualifierDataToggle = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierDataToggle;
	using CSS_QualifierDir = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierDir;
	using CSS_QualifierFrame = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierFrame;
	using CSS_QualifierHighlight = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierHighlight;
	using CSS_QualifierRole = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierRole;
	using CSS_QualifierRow = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierRow;
	using CSS_QualifierType = com.eagle.programmar.CSS.Qualifiers.CSS_QualifierType;
	using CSS_KeywordChoice = com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
	using CSS_Punctuation = com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class CSS_Qualifier : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSS_QualifierChoice qual;
		public CSS_QualifierChoice qual;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSS_Punctuation greaterThan = new com.eagle.programmar.CSS.Terminals.CSS_Punctuation('>');
		public  OPT;

		public class CSS_QualifierChoice : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_KeywordChoice XXchoice = new com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice("has-element-focus", "multiple", "readonly", "selected", "size", "subframe");
			public CSS_KeywordChoice XXchoice = new CSS_KeywordChoice("has-element-focus", "multiple", "readonly", "selected", "size", "subframe");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierClass XXqualifierClass;
			public CSS_QualifierClass XXqualifierClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualiferCode XXqualifierCode;
			public CSS_QualiferCode XXqualifierCode;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierDataToggle XXqualifierDataToggle;
			public CSS_QualifierDataToggle XXqualifierDataToggle;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierDir XXqualifierDir;
			public CSS_QualifierDir XXqualifierDir;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierFrame XXqualifierFrame;
			public CSS_QualifierFrame XXqualifierFrame;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierHighlight XXqualifierHighlight;
			public CSS_QualifierHighlight XXqualifierHighlight;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierRole XXqualifierRole;
			public CSS_QualifierRole XXqualifierRole;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierRow XXqualifierRow;
			public CSS_QualifierRow XXqualifierRow;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_QualifierType XXqualifierType;
			public CSS_QualifierType XXqualifierType;
		}
	}

}
