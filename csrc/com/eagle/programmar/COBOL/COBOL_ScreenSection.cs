// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

namespace com.eagle.programmar.COBOL
{
	using COBOL_BackgroundColor = com.eagle.programmar.COBOL.Screen.COBOL_BackgroundColor;
	using COBOL_ForegroundColor = com.eagle.programmar.COBOL.Screen.COBOL_ForegroundColor;
	using COBOL_ScreenBlankScreen = com.eagle.programmar.COBOL.Screen.COBOL_ScreenBlankScreen;
	using COBOL_ScreenBlankWhenZero = com.eagle.programmar.COBOL.Screen.COBOL_ScreenBlankWhenZero;
	using COBOL_ScreenColumn = com.eagle.programmar.COBOL.Screen.COBOL_ScreenColumn;
	using COBOL_ScreenFrom = com.eagle.programmar.COBOL.Screen.COBOL_ScreenFrom;
	using COBOL_ScreenLine = com.eagle.programmar.COBOL.Screen.COBOL_ScreenLine;
	using COBOL_ScreenPicture = com.eagle.programmar.COBOL.Screen.COBOL_ScreenPicture;
	using COBOL_ScreenPictureVariable = com.eagle.programmar.COBOL.Screen.COBOL_ScreenPictureVariable;
	using COBOL_ScreenPrompt = com.eagle.programmar.COBOL.Screen.COBOL_ScreenPrompt;
	using COBOL_ScreenTo = com.eagle.programmar.COBOL.Screen.COBOL_ScreenTo;
	using COBOL_ScreenUsing = com.eagle.programmar.COBOL.Screen.COBOL_ScreenUsing;
	using COBOL_ScreenValue = com.eagle.programmar.COBOL.Screen.COBOL_ScreenValue;
	using COBOL_Data_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
	using COBOL_Comment = com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_KeywordChoice = com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
	using COBOL_Level = com.eagle.programmar.COBOL.Terminals.COBOL_Level;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class COBOL_ScreenSection : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SCREEN = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SCREEN");
		public COBOL_Keyword SCREEN = new COBOL_Keyword("SCREEN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword SECTION = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("SECTION");
		public COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
		public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<COBOL_CopyOrScreenDeclaration> elements;
		public TokenList<COBOL_CopyOrScreenDeclaration> elements;

		public class COBOL_CopyOrScreenDeclaration : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Copy_Directive XXcopyBook;
			public COBOL_Copy_Directive XXcopyBook;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Comment XXcomment;
			public COBOL_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenDeclaration XXscreenDeclaration;
			public COBOL_ScreenDeclaration XXscreenDeclaration;
		}

		public class COBOL_ScreenDeclaration : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Level level;
			public COBOL_Level level;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) COBOL_ScreenContext context;
			public COBOL_ScreenContext context;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;

			public class COBOL_ScreenContext : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class COBOL_ScreenName extends com.eagle.tokens.TokenSequence
				public class COBOL_ScreenName : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) COBOL_ScreenFieldName fieldName;
					public COBOL_ScreenFieldName fieldName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<COBOL_ScreenClause> clauses;
					public  OPT;
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ScreenClauses extends com.eagle.tokens.TokenSequence
				public class COBOL_ScreenClauses : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<COBOL_ScreenClause> clauses;
					public TokenList<COBOL_ScreenClause> clauses;
				}
			}

			public class COBOL_ScreenFieldName : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Keyword XXFILLER = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FILLER");
				public COBOL_Keyword XXFILLER = new COBOL_Keyword("FILLER");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Data_Definition XXunusable_id;
				public COBOL_Data_Definition XXunusable_id;
			}

			public class COBOL_ScreenClause : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Comment XXcomment;
				public COBOL_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_Literal XXliteral;
				public COBOL_Literal XXliteral;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_KeywordChoice XXoption = new com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice("AUTO", "BLINK", "HIGHLIGHT", "NO-ECHO", "REVERSE-VIDEO", "SECURE");
				public COBOL_KeywordChoice XXoption = new COBOL_KeywordChoice("AUTO", "BLINK", "HIGHLIGHT", "NO-ECHO", "REVERSE-VIDEO", "SECURE");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenBlankScreen XXblankScreen;
				public COBOL_ScreenBlankScreen XXblankScreen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenBlankWhenZero XXblankZero;
				public COBOL_ScreenBlankWhenZero XXblankZero;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenLine XXline;
				public COBOL_ScreenLine XXline;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenColumn XXcolumn;
				public COBOL_ScreenColumn XXcolumn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_BackgroundColor XXbgColor;
				public COBOL_BackgroundColor XXbgColor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ForegroundColor XXfgColor;
				public COBOL_ForegroundColor XXfgColor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenValue XXvalue;
				public COBOL_ScreenValue XXvalue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenPicture XXpicture;
				public COBOL_ScreenPicture XXpicture;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenPictureVariable XXpictureValue;
				public COBOL_ScreenPictureVariable XXpictureValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenFrom XXfrom;
				public COBOL_ScreenFrom XXfrom;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenTo XXto;
				public COBOL_ScreenTo XXto;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenUsing XXusing;
				public COBOL_ScreenUsing XXusing;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE COBOL_ScreenPrompt XXprompt;
				public COBOL_ScreenPrompt XXprompt;
			}
		}
	}

}
