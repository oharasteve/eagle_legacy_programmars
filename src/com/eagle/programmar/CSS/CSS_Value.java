// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Terminals.CSS_Base64;
import com.eagle.programmar.CSS.Terminals.CSS_Color;
import com.eagle.programmar.CSS.Terminals.CSS_FileName;
import com.eagle.programmar.CSS.Terminals.CSS_HexNumber;
import com.eagle.programmar.CSS.Terminals.CSS_Identifier;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Number;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CSS_Value extends TokenChooser
{
	public @CHOICE CSS_HexNumber hex;
	public @CHOICE CSS_Color color;
	public @CHOICE CSS_Literal lit;
	public @FIRST CSS_Gradient gradient;
	
	public @LAST static class CSS_IdentifierValue extends TokenSequence
	{
		public @S(10) CSS_Identifier id;
		public @S(20) @OPT CSS_FunctionCall functionCall;
		
		public static class CSS_FunctionCall extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) @OPT SeparatedList<CSS_Value,PunctuationComma> values;
			public @S(30) PunctuationRightParen rightParen;
		}
	}
	
	public @CHOICE static class CSS_NumericValue extends TokenSequence
	{
		public @S(10) CSS_Number number;
		public @S(20) @OPT CSS_NumericSuffix suffix;
		public @S(30) @OPT CSS_SlashNumber slash;
		
		public static class CSS_NumericSuffix extends TokenChooser
		{
			public @CHOICE CSS_KeywordChoice units = new CSS_KeywordChoice("px", "em", "s", "in", "deg");
			public @CHOICE CSS_Punctuation percent = new CSS_Punctuation('%');
		}
		
		public static class CSS_SlashNumber extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CSS_Number number;
			public @S(30) @OPT CSS_NumericSuffix suffix;
		}
	}
	
	public @CHOICE static class CSS_Important extends TokenSequence
	{
		public @S(10) CSS_Punctuation exclamation = new CSS_Punctuation('!');
		public @S(20) CSS_Keyword IMPORTANT = new CSS_Keyword("important");
	}
	
	public @CHOICE static class CSS_Format extends TokenSequence
	{
		public @S(10) CSS_Keyword FORMAT = new CSS_Keyword("format");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Literal literal;
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Attr extends TokenSequence
	{
		public @S(10) CSS_Keyword ATTR = new CSS_Keyword("attr");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_KeywordChoice CODE = new CSS_KeywordChoice(
				"href",
				"title");
		public @S(40) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Rect extends TokenSequence
	{
		public @S(10) CSS_Keyword RECT = new CSS_Keyword("rect");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number num1;
		public @S(40) @OPT PunctuationComma comma1;
		public @S(50) CSS_Number num2;
		public @S(60) @OPT PunctuationComma comma2;
		public @S(70) CSS_Number num3;
		public @S(80) @OPT PunctuationComma comma3;
		public @S(90) CSS_Number num4;
		public @S(100) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Alpha_Value extends TokenSequence
	{
		public @S(10) CSS_Keyword ALPHA = new CSS_Keyword("alpha");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Keyword OPACITY = new CSS_Keyword("opacity");
		public @S(40) PunctuationEquals equals;
		public @S(50) CSS_Number number;
		public @S(60) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Translate3d extends TokenSequence
	{
		public @S(10) CSS_Keyword TRANSLATE3D = new CSS_Keyword("translate3d");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number number1;
		public @S(40) PunctuationComma comma1;
		public @S(50) CSS_Number number2;
		public @S(60) PunctuationComma comma2;
		public @S(70) CSS_Number number3;
		public @S(80) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Cubic_Bezier extends TokenSequence
	{
		public @S(10) CSS_Keyword CUBIC_BEZIER = new CSS_Keyword("cubic-bezier");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number number1;
		public @S(40) PunctuationComma comma1;
		public @S(50) CSS_Number number2;
		public @S(60) PunctuationComma comma2;
		public @S(70) CSS_Number number3;
		public @S(80) PunctuationComma comma3;
		public @S(90) CSS_Number number4;
		public @S(100) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_RotateValue extends TokenSequence
	{
		public @S(10) CSS_Keyword ROTATE = new CSS_Keyword("rotate");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number number;
		public @S(40) CSS_Keyword DEG = new CSS_Keyword("deg");
		public @S(50) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_URL_Value extends TokenSequence
	{
		public @S(10) CSS_Keyword URL = new CSS_Keyword("url");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_File file;
		public @S(40) PunctuationRightParen rightParen;
		
		public static class CSS_File extends TokenChooser
		{
			public @CHOICE CSS_FileName fileName;
			
			public @CHOICE static class CSS_FileInline extends TokenSequence
			{
				public @S(10) CSS_Keyword DATA = new CSS_Keyword("data");
				public @S(20) PunctuationColon colon;
				public @S(30) CSS_Keyword IMAGE = new CSS_Keyword("image");
				public @S(40) PunctuationSlash slash;
				public @S(50) CSS_Keyword PNG = new CSS_Keyword("png");
				public @S(60) PunctuationSemicolon semicolon;
				public @S(70) CSS_Keyword BASE64 = new CSS_Keyword("base64");
				public @S(80) PunctuationComma comma;
				public @S(90) CSS_Base64 base64;
			}
		}
	}
	
	public @CHOICE static class CSS_Scale extends TokenSequence
	{
		public @S(10) CSS_Keyword SCALE = new CSS_Keyword("scale");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Number number1;
		public @S(40) PunctuationComma comma2;
		public @S(50) CSS_Number number2;
		public @S(60) PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class CSS_Microsoft_Gradient extends TokenSequence
	{
		public @S(10) CSS_Keyword PROGID = new CSS_Keyword("progid");
		public @S(20) PunctuationColon colon;
		public @S(30) CSS_Keyword IMAGETRANSFORM = new CSS_Keyword("DXImageTransform");
		public @S(40) PunctuationPeriod dot1;
		public @S(50) CSS_Keyword MICROSOFT = new CSS_Keyword("Microsoft");
		public @S(60) PunctuationPeriod dot2;
		public @S(70) CSS_Keyword GRADIENT = new CSS_Keyword("gradient");
		public @S(80) PunctuationLeftParen leftParen;
		public @S(90) CSS_MS_GradientPiece piece;
		public @S(100) @OPT TokenList<CSS_More_MS_GradientPieces> morePieces;
		public @S(110) PunctuationRightParen rightParen;
		
		public static class CSS_MS_GradientPiece extends TokenSequence
		{
			public @S(10) CSS_KeywordChoice gradPiece = new CSS_KeywordChoice(
					"startColorStr", "endColorStr", "GradientType", "enabled");
			public @S(20) PunctuationEquals equals;
			public @S(30) CSS_Value value;
		}
		
		public static class CSS_More_MS_GradientPieces extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) CSS_MS_GradientPiece piece;
		}
	}
	
	public @CHOICE static class CSS_Webkit_Image_Set extends TokenSequence
	{
		public @S(10) CSS_Keyword WEBKIT_IMAGE_GET = new CSS_Keyword("-webkit-image-set");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) CSS_Webkit_Value piece;
		public @S(40) @OPT TokenList<CSS_More_WebkitPieces> morePieces;
		public @S(50) PunctuationRightParen rightParen;
		
		public static class CSS_Webkit_Value extends TokenSequence
		{
			public @S(10) CSS_Value url;
			public @S(20) CSS_KeywordChoice factor = new CSS_KeywordChoice("1x", "2x", "3x");
		}
		
		public static class CSS_More_WebkitPieces extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) CSS_Webkit_Value value;
		}
	}
}
