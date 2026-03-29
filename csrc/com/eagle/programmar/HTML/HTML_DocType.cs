// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2014

namespace com.eagle.programmar.HTML
{
	using HTML_Value = com.eagle.programmar.HTML.HTML_Attribute.HTML_Value;
	using HTML_Comment = com.eagle.programmar.HTML.Terminals.HTML_Comment;
	using HTML_KeywordChoice = com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using HTML_PunctuationChoice = com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class HTML_DocType : TokenSequence
	{
		internal static string[] SUFFIXES = new string[] {"+", "?", "*"};

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation startTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation("<!");
		public HTML_Punctuation startTag = new HTML_Punctuation("<!");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_KeywordChoice DOCTYPE = new com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice("ATTLIST", "DOCTYPE", "ELEMENT", "ENTITY", "NOTATION");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<HTML_DocValue> values;
		public TokenList<HTML_DocValue> values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE HTML_Punctuation endTag = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('>');
		public  NOSPACE;

		public class HTML_DocValue : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Value XXvalue;
			public HTML_Value XXvalue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_DocType XXdocType;
			public HTML_DocType XXdocType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Comment XXcomment;
			public HTML_Comment XXcomment;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_DocBrackets extends com.eagle.tokens.TokenSequence
			public class HTML_DocBrackets : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
				public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<HTML_DocValue> values;
				public TokenList<HTML_DocValue> values;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
				public PunctuationRightBracket rightBracket;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_DocParens extends com.eagle.tokens.TokenSequence
			public class HTML_DocParens : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) HTML_DocValue value;
				public HTML_DocValue value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT HTML_PunctuationChoice suffix = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice(SUFFIXES);
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<HTML_DocMoreValues> more;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT HTML_Punctuation plus = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('+');
				public  OPT;

				public class HTML_DocMoreValues : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice commaOrBar = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice(",", "|");
					public HTML_PunctuationChoice commaOrBar = new HTML_PunctuationChoice(",", "|");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) HTML_DocValue value;
					public HTML_DocValue value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT HTML_PunctuationChoice suffix = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice(SUFFIXES);
					public  OPT;
				}
			}
		}
	}

}
