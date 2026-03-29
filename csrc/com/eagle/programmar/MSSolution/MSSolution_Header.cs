// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution
{
	using MSSolution_Comment = com.eagle.programmar.MSSolution.Terminals.MSSolution_Comment;
	using MSSolution_EndOfLine = com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
	using MSSolution_Integer = com.eagle.programmar.MSSolution.Terminals.MSSolution_Integer;
	using MSSolution_Keyword = com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class MSSolution_Header : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) MSSolution_HeaderMicrosoft microsoft;
		public MSSolution_HeaderMicrosoft microsoft;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT MSSolution_CommentLine comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT MSSolution_HeaderVisual visual;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT MSSolution_HeaderMinimum minimum;
		public  OPT;

		public class MSSolution_CommentLine : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Comment comment;
			public MSSolution_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
			public MSSolution_EndOfLine eoln;
		}

		public class MSSolution_HeaderMicrosoft : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword MICROSOFT = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Microsoft");
			public MSSolution_Keyword MICROSOFT = new MSSolution_Keyword("Microsoft");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword VISUAL = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Visual");
			public MSSolution_Keyword VISUAL = new MSSolution_Keyword("Visual");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword STUDIO = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Studio");
			public MSSolution_Keyword STUDIO = new MSSolution_Keyword("Studio");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword SOLUTION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Solution");
			public MSSolution_Keyword SOLUTION = new MSSolution_Keyword("Solution");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword FILE = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("File");
			public MSSolution_Keyword FILE = new MSSolution_Keyword("File");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword FORMAT = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Format");
			public MSSolution_Keyword FORMAT = new MSSolution_Keyword("Format");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword VERSION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Version");
			public MSSolution_Keyword VERSION = new MSSolution_Keyword("Version");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.SeparatedList<com.eagle.programmar.MSSolution.Terminals.MSSolution_Integer, com.eagle.tokens.punctuation.PunctuationPeriod> version;
			public SeparatedList<MSSolution_Integer, PunctuationPeriod> version;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
			public MSSolution_EndOfLine eoln;
		}

		public class MSSolution_HeaderVisual : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword VISUALSTUDIOVERSION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("VisualStudioVersion");
			public MSSolution_Keyword VISUALSTUDIOVERSION = new MSSolution_Keyword("VisualStudioVersion");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.MSSolution.Terminals.MSSolution_Integer, com.eagle.tokens.punctuation.PunctuationPeriod> version;
			public SeparatedList<MSSolution_Integer, PunctuationPeriod> version;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
			public MSSolution_EndOfLine eoln;
		}

		public class MSSolution_HeaderMinimum : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword MINIMUMVISUALSTUDIOVERSION = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("MinimumVisualStudioVersion");
			public MSSolution_Keyword MINIMUMVISUALSTUDIOVERSION = new MSSolution_Keyword("MinimumVisualStudioVersion");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.MSSolution.Terminals.MSSolution_Integer, com.eagle.tokens.punctuation.PunctuationPeriod> version;
			public SeparatedList<MSSolution_Integer, PunctuationPeriod> version;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln;
			public MSSolution_EndOfLine eoln;
		}
	}

}
