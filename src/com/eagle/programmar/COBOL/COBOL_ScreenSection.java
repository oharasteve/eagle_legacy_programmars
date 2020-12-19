// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Picture;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

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

			public @CHOICE COBOL_KeywordChoice option = new COBOL_KeywordChoice(
					"AUTO",
					"BLINK",
					"HIGHLIGHT",
					"NO-ECHO",
					"REVERSE-VIDEO",
					"SECURE");
			
			public @CHOICE static class COBOL_ScreenBlankScreen extends TokenSequence
			{
				public @S(10) COBOL_Keyword BLANK = new COBOL_Keyword("BLANK");
				public @S(20) COBOL_KeywordChoice blank = new COBOL_KeywordChoice("SCREEN", "LINE");
			}
			
			public @CHOICE static class COBOL_ScreenBlankWhenZero extends TokenSequence
			{
				public @S(10) COBOL_Keyword BLANK = new COBOL_Keyword("BLANK");
				public @S(20) @OPT COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
				public @S(30) COBOL_Keyword ZERO = new COBOL_Keyword("ZERO");
			}
			
			public @CHOICE static class COBOL_ScreenLine extends TokenSequence
			{
				public @S(10) COBOL_Keyword LINE = new COBOL_Keyword("LINE");
				public @S(20) @OPT COBOL_Punctuation plus = new COBOL_Punctuation('+');
				public @S(30) COBOL_NumberOrIdentifier number;
			}

			public @CHOICE static class COBOL_ScreenColumn extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice COLUMN = new COBOL_KeywordChoice("COLUMN", "COL");
				public @S(20) @OPT COBOL_NumberOrIdentifier number;
			}

			public @CHOICE static class COBOL_BackgroundColor extends TokenSequence
			{
				public @S(10) COBOL_Keyword BACKGROUND = new COBOL_Keyword("BACKGROUND-COLOR");
				public @S(20) @OPT COBOL_NumberOrIdentifier color;
			}

			public @CHOICE static class COBOL_ForegroundColor extends TokenSequence
			{
				public @S(10) COBOL_Keyword FOREGROUND = new COBOL_Keyword("FOREGROUND-COLOR");
				public @S(20) @OPT COBOL_NumberOrIdentifier color;
			}

			public @CHOICE static class COBOL_ScreenValue extends TokenSequence
			{
				public @S(10) COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
				public @S(20) @OPT COBOL_Literal value;
			}
			
			public @CHOICE static class COBOL_ScreenPicture extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice PIC = new COBOL_KeywordChoice("PIC", "PICTURE");
				public @S(20) COBOL_Picture picture;
			}

			public @CHOICE static class COBOL_ScreenPictureVariable extends TokenSequence
			{
				public @S(10) COBOL_KeywordChoice PIC = new COBOL_KeywordChoice("PIC", "PICTURE");
				public @S(20) COBOL_Keyword X = new COBOL_Keyword("X");
				public @S(30) PunctuationLeftParen leftParen;
				public @S(40) COBOL_Expression size;
				public @S(50) PunctuationRightParen rightParen;
			}
			
			public @CHOICE static class COBOL_ScreenFrom extends TokenSequence
			{
				public @S(10) COBOL_Keyword FROM = new COBOL_Keyword("FROM");
				public @S(20) COBOL_Identifier_Reference dataRef;
				public @S(30) @OPT COBOL_Subscript subscript;
			}

			public @CHOICE static class COBOL_ScreenTo extends TokenSequence
			{
				public @S(10) COBOL_Keyword TO = new COBOL_Keyword("TO");
				public @S(20) COBOL_Identifier_Reference dataRef;
				public @S(30) @OPT COBOL_Subscript subscript;
			}

			public @CHOICE static class COBOL_ScreenUsing extends TokenSequence
			{
				public @S(10) COBOL_Keyword USING = new COBOL_Keyword("USING");
				public @S(20) @OPT COBOL_Identifier_Reference dataRef;
				public @S(30) @OPT COBOL_Subscript subscript;
			}
			
			public @CHOICE static class COBOL_ScreenPrompt extends TokenSequence
			{
				public @S(10) COBOL_Keyword PROMPT = new COBOL_Keyword("PROMPT");
				public @S(20) COBOL_Literal literal;
			}
		}

		public static class COBOL_NumberOrIdentifier extends TokenChooser
		{
			public @CHOICE COBOL_Number color;
			public @CHOICE COBOL_Identifier_Reference dataRef;
		}
	}
}
