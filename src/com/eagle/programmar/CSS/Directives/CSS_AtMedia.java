// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.CSS_Program.CSS_Body;
import com.eagle.programmar.CSS.CSS_Program.CSS_Line;
import com.eagle.programmar.CSS.Terminals.CSS_Comment;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_AtMedia extends TokenSequence
{
	public @S(10) CSS_Punctuation at = new CSS_Punctuation('@');
	public @S(20) CSS_Keyword MEDIA = new CSS_Keyword("media");
	public @S(30) CSS_MediaParam param;
	public @S(40) @OPT TokenList<CSS_MoreMediaParam> moreParams;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT CSS_Comment comment;
	public @S(70) TokenList<CSS_Body> bodies;
	public @S(80) PunctuationRightBrace rightBrace;
	
	public static class CSS_MediaParam extends TokenChooser
	{
		public @CHOICE CSS_Keyword ALL = new CSS_Keyword("all");
		
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
		public @S(10) @OPT PunctuationComma comma;
		public @S(20) @OPT CSS_Keyword AND = new CSS_Keyword("and");
		public @S(30) CSS_MediaParam param;
	}
}
