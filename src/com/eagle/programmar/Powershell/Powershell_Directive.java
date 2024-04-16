// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 22, 2022

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_Directive extends TokenSequence
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) SeparatedList<Powershell_WhichDirective, PunctuationComma> directives;
	public @S(30) PunctuationRightBracket rightBracket;
	public @S(40) @OPT Powershell_DirectiveRedirect redirect;
	public @S(50) @OPT Powershell_Comment comment;
	public @S(60) Powershell_RealEndOfLine eoln;
	
	public static class Powershell_WhichDirective extends TokenChooser
	{
		// [Diagnostics.CodeAnalysis.SuppressMessageAttribute("PSUseLiteralInitializerForHashtable", "")]
		public @CHOICE static class Powershell_DiagnosticsDirective extends TokenSequence
		{
			public @S(10) Powershell_Keyword DIAGNOSTICS = new Powershell_Keyword("Diagnostics");
			public @S(20) PunctuationPeriod dot1;
			public @S(30) Powershell_Keyword CODEANALYSIS = new Powershell_Keyword("CodeAnalysis");
			public @S(40) PunctuationPeriod dot2;
			public @S(50) Powershell_Keyword SUPPRESS = new Powershell_Keyword("SuppressMessageAttribute");
			public @S(60) PunctuationLeftParen leftParen;
			public @S(70) Powershell_Literal literal1;
			public @S(80) PunctuationComma comma;
			public @S(90) Powershell_Literal literal2;
			public @S(100) PunctuationRightParen rightParen;
		}
		
	    // [Windows.Storage.StorageFile, Windows.Storage, ContentType=WindowsRuntime] | Out-Null
	    // [Windows.Graphics.Imaging.BitmapDecoder, Windows.Graphics, ContentType=WindowsRuntime] | Out-Null
		public @FIRST static class Powershell_WindowsDirective1 extends TokenSequence
		{
			public @S(10) Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
			public @S(20) PunctuationPeriod dot1;
			public @S(30) Powershell_Keyword STORAGE = new Powershell_Keyword("Storage");
			public @S(40) PunctuationPeriod dot2;
			public @S(50) Powershell_Keyword STORAGE_FILE = new Powershell_Keyword("StorageFile");
		}

		public @FIRST static class Powershell_WindowsDirective2 extends TokenSequence
		{
			public @S(10) Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
			public @S(20) PunctuationPeriod dot1;
			public @S(30) Powershell_Keyword GRAPHICS = new Powershell_Keyword("Graphics");
			public @S(40) PunctuationPeriod dot2;
			public @S(50) Powershell_Keyword IMAGING = new Powershell_Keyword("Imaging");
			public @S(60) PunctuationPeriod dot3;
			public @S(70) Powershell_Keyword BITMAP_DECODER = new Powershell_Keyword("BitmapDecoder");
		}

		public @CHOICE static class Powershell_WindowsDirective3 extends TokenSequence
		{
			public @S(10) Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
			public @S(20) PunctuationPeriod dot;
			public @S(30) Powershell_Keyword STORAGE = new Powershell_Keyword("Storage");
		}

		public @CHOICE static class Powershell_WindowsDirective4 extends TokenSequence
		{
			public @S(10) Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
			public @S(20) PunctuationPeriod dot;
			public @S(30) Powershell_Keyword GRAPHICS = new Powershell_Keyword("Graphics");
		}

		public @CHOICE static class Powershell_ContentTypeDirective extends TokenSequence
		{
			public @S(10) Powershell_Keyword CONTENT_TYPE = new Powershell_Keyword("ContentType");
			public @S(20) PunctuationEquals equals;
			public @S(30) Powershell_Keyword RUNTIME = new Powershell_Keyword("WindowsRuntime");
		}
	}
	
	public static class Powershell_DirectiveRedirect extends TokenSequence
	{
		public @S(10) Powershell_Punctuation bar = new Powershell_Punctuation('|');
		public @S(20) Powershell_Keyword OUT_NULL = new Powershell_Keyword("Out-Null");
	}
}
