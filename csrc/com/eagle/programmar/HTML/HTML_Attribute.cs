// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

namespace com.eagle.programmar.HTML
{
	using HTML_HexNumber = com.eagle.programmar.HTML.Terminals.HTML_HexNumber;
	using HTML_Identifier = com.eagle.programmar.HTML.Terminals.HTML_Identifier;
	using HTML_Keyword = com.eagle.programmar.HTML.Terminals.HTML_Keyword;
	using HTML_Literal = com.eagle.programmar.HTML.Terminals.HTML_Literal;
	using HTML_Number = com.eagle.programmar.HTML.Terminals.HTML_Number;
	using HTML_Punctuation = com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
	using HTML_PunctuationChoice = com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class HTML_Attribute : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) HTML_IdentifierOrKeyword attribute;
		public HTML_IdentifierOrKeyword attribute;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT HTML_AttributeValue val;
		public  OPT;

		public class HTML_AttributeValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationEquals equals;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE HTML_Value value;
			public  NOSPACE;
		}

		public class HTML_IdentifierOrKeyword : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Identifier XXattribute;
			public HTML_Identifier XXattribute;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE HTML_Keyword XXstyle = new com.eagle.programmar.HTML.Terminals.HTML_Keyword("style");
			public HTML_Keyword XXstyle = new HTML_Keyword("style");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_Namespace extends com.eagle.tokens.TokenSequence
			public class HTML_Namespace : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Identifier id1;
				public HTML_Identifier id1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Identifier id2;
				public HTML_Identifier id2;
			}
		}

		public class HTML_Value : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST HTML_HexNumber XXhex;
			public HTML_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST HTML_Number XXnumber;
			public HTML_Number XXnumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST HTML_Literal XXliteral;
			public HTML_Literal XXliteral;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_Id_Value extends com.eagle.tokens.TokenSequence
			public class HTML_Id_Value : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.HTML.Terminals.HTML_Identifier, com.eagle.tokens.punctuation.PunctuationComma> id;
				public SeparatedList<HTML_Identifier, PunctuationComma> id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT HTML_Id_DotValue dotValue;
				public  OPT;

				public class HTML_Id_DotValue : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice dotOrColon = new com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice(".", ":");
					public HTML_PunctuationChoice dotOrColon = new HTML_PunctuationChoice(".", ":");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.HTML.Terminals.HTML_Identifier id;
					public HTML_Identifier id;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_Label extends com.eagle.tokens.TokenSequence
			public class HTML_Label : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation poundSign = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('#');
				public HTML_Punctuation poundSign = new HTML_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.HTML.Terminals.HTML_Identifier label;
				public HTML_Identifier label;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class HTML_Strange_Number extends com.eagle.tokens.TokenSequence
			public class HTML_Strange_Number : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.HTML.Terminals.HTML_Punctuation plus = new com.eagle.programmar.HTML.Terminals.HTML_Punctuation('+');
				public HTML_Punctuation plus = new HTML_Punctuation('+');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod point;
				public PunctuationPeriod point;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.HTML.Terminals.HTML_Number number;
				public HTML_Number number;
			}
		}
	}

}
