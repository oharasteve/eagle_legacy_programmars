// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

namespace com.eagle.programmar.COBOL
{
	using COBOL_Identifier_Reference = com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_ReportDataLineType : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDataLineTypeReportHeading extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDataLineTypeReportHeading : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword REPORT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("REPORT");
			public COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword HEADING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("HEADING");
			public COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDataLineTypeReportFooting extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDataLineTypeReportFooting : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword REPORT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("REPORT");
			public COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FOOTING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FOOTING");
			public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDataLineTypePageHeading extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDataLineTypePageHeading : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PAGE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PAGE");
			public COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword HEADING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("HEADING");
			public COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDataLineTypeDetail extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDataLineTypeDetail : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DETAIL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DETAIL");
			public COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDataLineTypePageFooting extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDataLineTypePageFooting : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PAGE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PAGE");
			public COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FOOTING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FOOTING");
			public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDataLineTypeControlFooting extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDataLineTypeControlFooting : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CONTROL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CONTROL");
			public COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FOOTING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FOOTING");
			public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword FINAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FINAL");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT COBOL_Identifier_Reference id;
			public  OPT;
		}
	}
}
