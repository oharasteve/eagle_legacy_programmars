// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Statements
{
	using JavaP_EndOfLine = com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
	using JavaP_HexNoPrefix = com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
	using JavaP_Keyword = com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
	using JavaP_KeywordChoice = com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
	using JavaP_Number = com.eagle.programmar.JavaP.Terminals.JavaP_Number;
	using JavaP_QualifiedName = com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class JavaP_Classfile : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword CLASSFILE = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Classfile");
		public JavaP_Keyword CLASSFILE = new JavaP_Keyword("Classfile");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName filename;
		public JavaP_QualifiedName filename;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
		public JavaP_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT JavaP_LastModified lastModified;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT JavaP_MD5Checksum checksum;
		public  OPT;

		public class JavaP_LastModified : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword LAST = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("Last");
			public JavaP_Keyword LAST = new JavaP_Keyword("Last");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword MODIFIED = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("modified");
			public JavaP_Keyword MODIFIED = new JavaP_Keyword("modified");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice MONTH = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
			public JavaP_KeywordChoice MONTH = new JavaP_KeywordChoice("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_Number day;
			public JavaP_Number day;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.JavaP.Terminals.JavaP_Number year;
			public JavaP_Number year;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
			public PunctuationSemicolon semicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword SIZE = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("size");
			public JavaP_Keyword SIZE = new JavaP_Keyword("size");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.JavaP.Terminals.JavaP_Number bytes;
			public JavaP_Number bytes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword BYTES = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("bytes");
			public JavaP_Keyword BYTES = new JavaP_Keyword("bytes");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;
		}

		public class JavaP_MD5Checksum : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice MD5 = new com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice("MD5", "SHA-256");
			public JavaP_KeywordChoice MD5 = new JavaP_KeywordChoice("MD5", "SHA-256");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.JavaP.Terminals.JavaP_Keyword CHECKSUM = new com.eagle.programmar.JavaP.Terminals.JavaP_Keyword("checksum");
			public JavaP_Keyword CHECKSUM = new JavaP_Keyword("checksum");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix hex;
			public JavaP_HexNoPrefix hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine eoln;
			public JavaP_EndOfLine eoln;
		}
	}

}
