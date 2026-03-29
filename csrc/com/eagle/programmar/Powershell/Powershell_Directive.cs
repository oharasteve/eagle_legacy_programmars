// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 22, 2022

namespace com.eagle.programmar.Powershell
{
	using Powershell_Comment = com.eagle.programmar.Powershell.Terminals.Powershell_Comment;
	using Powershell_Keyword = com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
	using Powershell_Literal = com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
	using Powershell_Punctuation = com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
	using Powershell_RealEndOfLine = com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Powershell_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<Powershell_WhichDirective, com.eagle.tokens.punctuation.PunctuationComma> directives;
		public SeparatedList<Powershell_WhichDirective, PunctuationComma> directives;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
		public PunctuationRightBracket rightBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Powershell_DirectiveRedirect redirect;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Powershell_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Powershell.Terminals.Powershell_RealEndOfLine eoln;
		public Powershell_RealEndOfLine eoln;

		public class Powershell_WhichDirective : TokenChooser
		{
			// [Diagnostics.CodeAnalysis.SuppressMessageAttribute("PSUseLiteralInitializerForHashtable",
			// "")]
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_DiagnosticsDirective extends com.eagle.tokens.TokenSequence
			public class Powershell_DiagnosticsDirective : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword DIAGNOSTICS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Diagnostics");
				public Powershell_Keyword DIAGNOSTICS = new Powershell_Keyword("Diagnostics");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
				public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword CODEANALYSIS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("CodeAnalysis");
				public Powershell_Keyword CODEANALYSIS = new Powershell_Keyword("CodeAnalysis");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
				public PunctuationPeriod dot2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword SUPPRESS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("SuppressMessageAttribute");
				public Powershell_Keyword SUPPRESS = new Powershell_Keyword("SuppressMessageAttribute");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Powershell.Terminals.Powershell_Literal literal1;
				public Powershell_Literal literal1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Powershell.Terminals.Powershell_Literal literal2;
				public Powershell_Literal literal2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}

			// [Windows.Storage.StorageFile, Windows.Storage, ContentType=WindowsRuntime] |
			// Out-Null
			// [Windows.Graphics.Imaging.BitmapDecoder, Windows.Graphics,
			// ContentType=WindowsRuntime] | Out-Null
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Powershell_WindowsDirective1 extends com.eagle.tokens.TokenSequence
			public class Powershell_WindowsDirective1 : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword WINDOWS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Windows");
				public Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
				public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword STORAGE = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Storage");
				public Powershell_Keyword STORAGE = new Powershell_Keyword("Storage");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
				public PunctuationPeriod dot2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword STORAGE_FILE = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("StorageFile");
				public Powershell_Keyword STORAGE_FILE = new Powershell_Keyword("StorageFile");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Powershell_WindowsDirective2 extends com.eagle.tokens.TokenSequence
			public class Powershell_WindowsDirective2 : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword WINDOWS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Windows");
				public Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot1;
				public PunctuationPeriod dot1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword GRAPHICS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Graphics");
				public Powershell_Keyword GRAPHICS = new Powershell_Keyword("Graphics");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot2;
				public PunctuationPeriod dot2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword IMAGING = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Imaging");
				public Powershell_Keyword IMAGING = new Powershell_Keyword("Imaging");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationPeriod dot3;
				public PunctuationPeriod dot3;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword BITMAP_DECODER = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("BitmapDecoder");
				public Powershell_Keyword BITMAP_DECODER = new Powershell_Keyword("BitmapDecoder");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_WindowsDirective3 extends com.eagle.tokens.TokenSequence
			public class Powershell_WindowsDirective3 : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword WINDOWS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Windows");
				public Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword STORAGE = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Storage");
				public Powershell_Keyword STORAGE = new Powershell_Keyword("Storage");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_WindowsDirective4 extends com.eagle.tokens.TokenSequence
			public class Powershell_WindowsDirective4 : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword WINDOWS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Windows");
				public Powershell_Keyword WINDOWS = new Powershell_Keyword("Windows");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword GRAPHICS = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Graphics");
				public Powershell_Keyword GRAPHICS = new Powershell_Keyword("Graphics");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Powershell_ContentTypeDirective extends com.eagle.tokens.TokenSequence
			public class Powershell_ContentTypeDirective : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword CONTENT_TYPE = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("ContentType");
				public Powershell_Keyword CONTENT_TYPE = new Powershell_Keyword("ContentType");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword RUNTIME = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("WindowsRuntime");
				public Powershell_Keyword RUNTIME = new Powershell_Keyword("WindowsRuntime");
			}
		}

		public class Powershell_DirectiveRedirect : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation bar = new com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation('|');
			public Powershell_Punctuation bar = new Powershell_Punctuation('|');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Powershell.Terminals.Powershell_Keyword OUT_NULL = new com.eagle.programmar.Powershell.Terminals.Powershell_Keyword("Out-Null");
			public Powershell_Keyword OUT_NULL = new Powershell_Keyword("Out-Null");
		}
	}

}
