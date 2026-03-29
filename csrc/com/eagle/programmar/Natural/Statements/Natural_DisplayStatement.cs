// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_FunctionCall = com.eagle.programmar.Natural.Natural_FunctionCall;
	using Natural_Option = com.eagle.programmar.Natural.Natural_Option;
	using Natural_SystemVariable = com.eagle.programmar.Natural.Natural_SystemVariable;
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Comment = com.eagle.programmar.Natural.Terminals.Natural_Comment;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_KeywordChoice = com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using Natural_Number = com.eagle.programmar.Natural.Terminals.Natural_Number;
	using Natural_Tab = com.eagle.programmar.Natural.Terminals.Natural_Tab;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;

	public class Natural_DisplayStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/display.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword DISPLAY = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DISPLAY");
		public @DOC("sm/display.htm") Natural_Keyword DISPLAY = new Natural_Keyword("DISPLAY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword NOTITLE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("NOTITLE");
		public @OPT Natural_Keyword NOTITLE = new Natural_Keyword("NOTITLE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_DisplayFunctions displayFns;
		public @OPT Natural_DisplayFunctions displayFns;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<Natural_DisplayElement> displayElement;
		public TokenList<Natural_DisplayElement> displayElement;

		public static class Natural_DisplayFunctions extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Natural_Keyword AND = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AND");
			public @OPT Natural_Keyword AND = new Natural_Keyword("AND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword GIVE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("GIVE");
			public @OPT Natural_Keyword GIVE = new Natural_Keyword("GIVE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Natural_Keyword SYSTEM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("SYSTEM");
			public @OPT Natural_Keyword SYSTEM = new Natural_Keyword("SYSTEM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Terminals.Natural_Keyword FUNCTIONS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FUNCTIONS");
			public Natural_Keyword FUNCTIONS = new Natural_Keyword("FUNCTIONS");
		}

		public static class Natural_DisplayElement extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Option XXdisplayOption;
			public Natural_Option XXdisplayOption;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSlash XXslash;
			public PunctuationSlash XXslash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Tab XXtab;
			public Natural_Tab XXtab;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Variable XXvar;
			public Natural_Variable XXvar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_Comment XXcomment;
			public Natural_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_SystemVariable XXsysVar;
			public Natural_SystemVariable XXsysVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_KeywordChoice XXoption = new com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice("HORIZ", "UNDERLINED", "TRAILER");
			public Natural_KeywordChoice XXoption = new Natural_KeywordChoice("HORIZ", "UNDERLINED", "TRAILER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_FunctionCall XXfnCall;
			public Natural_FunctionCall XXfnCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Natural_DisplayParameter XXparameter;
			public Natural_DisplayParameter XXparameter;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Justified extends com.eagle.tokens.TokenSequence
			public static class Natural_Justified extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword LEFT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("LEFT");
				public Natural_Keyword LEFT = new Natural_Keyword("LEFT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword JUSTIFIED = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("JUSTIFIED");
				public @OPT Natural_Keyword JUSTIFIED = new Natural_Keyword("JUSTIFIED");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_FieldPositioning extends com.eagle.tokens.TokenSequence
			public static class Natural_FieldPositioning extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword T = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("T");
				public Natural_Keyword T = new Natural_Keyword("T");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationStar star;
				public PunctuationStar star;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Variable var;
				public Natural_Variable var;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_FieldAndLinePositioning extends com.eagle.tokens.TokenSequence
			public static class Natural_FieldAndLinePositioning extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword P = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("P");
				public Natural_Keyword P = new Natural_Keyword("P");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationStar star;
				public PunctuationStar star;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Variable var;
				public Natural_Variable var;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Vertical extends com.eagle.tokens.TokenSequence
			public static class Natural_Vertical extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword VERT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("VERT");
				public Natural_Keyword VERT = new Natural_Keyword("VERT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword AS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AS");
				public @OPT Natural_Keyword AS = new Natural_Keyword("AS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
				public Natural_Literal literal;
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_VerticalCaptioned extends com.eagle.tokens.TokenSequence
			public static class Natural_VerticalCaptioned extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword VERT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("VERT");
				public Natural_Keyword VERT = new Natural_Keyword("VERT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword AS = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AS");
				public @OPT Natural_Keyword AS = new Natural_Keyword("AS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword CAPTIONED = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("CAPTIONED");
				public Natural_Keyword CAPTIONED = new Natural_Keyword("CAPTIONED");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Display_Literal extends com.eagle.tokens.TokenSequence
			public static class Natural_Display_Literal extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
				public Natural_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_LiteralCount count;
				public @OPT Natural_LiteralCount count;

				public static class Natural_LiteralCount extends TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
					public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Terminals.Natural_Number count;
					public Natural_Number count;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
					public PunctuationRightParen rightParen;
				}
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_Relative_Positioning extends com.eagle.tokens.TokenSequence
			public static class Natural_Relative_Positioning extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Number lines;
				public Natural_Number lines;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationSlash slash;
				public PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Number column;
				public Natural_Number column;
			}
		}
	}

}
