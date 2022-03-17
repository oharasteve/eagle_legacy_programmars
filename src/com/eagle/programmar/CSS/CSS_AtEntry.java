// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 26, 2016

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.CSS_Program.CSS_Body;
import com.eagle.programmar.CSS.CSS_Program.CSS_Line;
import com.eagle.programmar.CSS.CSS_Value.CSS_URL_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;
import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSS_AtEntry extends TokenChooser
{
	public @CHOICE static class CSS_Moz_Document extends TokenSequence
	{
		public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
		public @S(20) CSS_Keyword MOZDOCUMENT = new CSS_Keyword("-moz-document");
		public @S(30) CSS_Keyword URLPREFIX = new CSS_Keyword("url-prefix");
		public @S(40) @NOSPACE PunctuationLeftParen leftParen;
		public @S(50) @NOSPACE CSS_Literal literal;
		public @S(60) @NOSPACE PunctuationRightParen rightParen;
		public @S(70) @INDENT PunctuationLeftBrace leftBrace;
		public @S(80) TokenList<CSS_Body> bodies;
		public @S(90) @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
	}
	
	public @CHOICE static class CSS_AtMedia extends TokenSequence
	{
		public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
		public @S(20) @NOSPACE CSS_Keyword MEDIA = new CSS_Keyword("media");
		public @S(30) CSS_MediaParam param;
		public @S(40) @OPT TokenList<CSS_MoreMediaParam> moreParams;
		public @S(50) @INDENT PunctuationLeftBrace leftBrace;
		public @S(60) @OPT CSS_Comment comment;
		public @S(70) TokenList<CSS_Body> bodies;
		public @S(80) @NOSPACE @OUTDENT PunctuationRightBrace rightBrace;
		
		public static class CSS_MediaParam extends TokenChooser
		{
			public @CHOICE static class CSS_MediaParamParens extends TokenSequence
			{
				public @S(10) PunctuationLeftParen leftParen;
				public @S(20) CSS_Line line;
				public @S(30) PunctuationRightParen rightParen;
			}
			
			public @CHOICE static class CSS_MediaScreen extends TokenSequence
			{
				public @S(10) @OPT CSS_Keyword ONLY = new CSS_Keyword("only");
				public @S(20) CSS_Keyword SCREEN = new CSS_Keyword("screen");
			}
			
			public @CHOICE static class CSS_MediaPrint extends TokenSequence
			{
				public @S(10) CSS_Keyword PRINT = new CSS_Keyword("print");
			}
		}
		
		public static class CSS_MoreMediaParam extends TokenSequence
		{
			public @S(10) @OPT @NOSPACE PunctuationComma comma;
			public @S(20) @OPT CSS_Keyword AND = new CSS_Keyword("and");
			public @S(30) CSS_MediaParam param;
		}
	}
	
	public @CHOICE static class CSS_AtImport extends TokenSequence
	{
		public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
		public @S(20) @NOSPACE CSS_Keyword IMPORT = new CSS_Keyword("import");
		public @S(30) CSS_ImportWhat what;
		public @S(40) @NOSPACE PunctuationSemicolon semicolon;
		
		public static class CSS_ImportWhat extends TokenChooser
		{
			public @CHOICE CSS_FileName fileName;
			public @FIRST CSS_URL_Value urlValue;
		}
	}
	
	public @CHOICE static class CSS_AtCharset extends TokenSequence
	{
		public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
		public @S(20) @NOSPACE CSS_Keyword CHARSET = new CSS_Keyword("charset");
		public @S(30) CSS_Literal charset;
		public @S(40) @NOSPACE PunctuationSemicolon semicolon;
	}

	public @CHOICE static class CSS_AtNamespace extends TokenSequence
	{
		public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
		public @S(20) @NOSPACE CSS_Keyword NAMESPACE = new CSS_Keyword("namespace");
		public @S(30) CSS_AtNameSpaceArg arg;
		public @S(40) @NOSPACE PunctuationSemicolon semicolon;
		
		public static class CSS_AtNameSpaceArg extends TokenChooser
		{
			public @CHOICE CSS_Literal literal;
			
			public @CHOICE static class CSS_AtNameSpaceURL extends TokenSequence
			{
				public @S(10) CSS_Identifier name;
				public @S(20) CSS_Keyword URL = new CSS_Keyword("URL");
				public @S(30) PunctuationLeftParen leftParen;
				public @S(40) CSS_FileName url;
				public @S(50) PunctuationRightParen rightParen;
			}
		}
	}
}
