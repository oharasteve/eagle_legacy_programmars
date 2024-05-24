// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Screen.COBOL_BackgroundColor;
import com.eagle.programmar.COBOL.Screen.COBOL_ForegroundColor;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenBlankScreen;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenBlankWhenZero;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenColumn;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenFrom;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenLine;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenPicture;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenPictureVariable;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenPrompt;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenTo;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenUsing;
import com.eagle.programmar.COBOL.Screen.COBOL_ScreenValue;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_ScreenSection extends TokenSequence
{
	public @S(10) COBOL_Keyword SCREEN = new COBOL_Keyword("SCREEN");
	public @S(20) COBOL_Keyword SECTION = new COBOL_Keyword("SECTION");
	public @S(30) PunctuationPeriod dot;
	public @S(40) TokenList<COBOL_CopyOrScreenDeclaration> elements;

	public static class COBOL_CopyOrScreenDeclaration extends TokenChooser
	{
		public @CHOICE COBOL_Copy_Directive copyBook;
		public @CHOICE COBOL_Comment comment;
		public @CHOICE COBOL_ScreenDeclaration screenDeclaration;
	}

	public static class COBOL_ScreenDeclaration extends TokenSequence
	{
		public @S(10) COBOL_Level level;
		public @S(20) COBOL_ScreenContext context;
		public @S(30) PunctuationPeriod dot;

		public static class COBOL_ScreenContext extends TokenChooser
		{
			public @LAST static class COBOL_ScreenName extends TokenSequence
			{
				public @S(10) COBOL_ScreenFieldName fieldName;
				public @S(20) @OPT TokenList<COBOL_ScreenClause> clauses;
			}

			public @CHOICE static class COBOL_ScreenClauses extends TokenSequence
			{
				public @S(10) TokenList<COBOL_ScreenClause> clauses;
			}
		}

		public static class COBOL_ScreenFieldName extends TokenChooser
		{
			public @CHOICE COBOL_Keyword FILLER = new COBOL_Keyword("FILLER");
			public @CHOICE COBOL_Data_Definition unusable_id;
		}

		public static class COBOL_ScreenClause extends TokenChooser
		{
			public @CHOICE COBOL_Comment comment;
			public @CHOICE COBOL_Literal literal;

			public @CHOICE COBOL_KeywordChoice option = new COBOL_KeywordChoice("AUTO", "BLINK", "HIGHLIGHT", "NO-ECHO",
					"REVERSE-VIDEO", "SECURE");

			public @CHOICE COBOL_ScreenBlankScreen blankScreen;
			public @CHOICE COBOL_ScreenBlankWhenZero blankZero;
			public @CHOICE COBOL_ScreenLine line;
			public @CHOICE COBOL_ScreenColumn column;
			public @CHOICE COBOL_BackgroundColor bgColor;
			public @CHOICE COBOL_ForegroundColor fgColor;
			public @CHOICE COBOL_ScreenValue value;
			public @CHOICE COBOL_ScreenPicture picture;
			public @CHOICE COBOL_ScreenPictureVariable pictureValue;
			public @CHOICE COBOL_ScreenFrom from;
			public @CHOICE COBOL_ScreenTo to;
			public @CHOICE COBOL_ScreenUsing using;
			public @CHOICE COBOL_ScreenPrompt prompt;
		}
	}
}
