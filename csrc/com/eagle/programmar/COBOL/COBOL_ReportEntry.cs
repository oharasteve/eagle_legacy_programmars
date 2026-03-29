// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

namespace com.eagle.programmar.COBOL
{
	using COBOL_ReportLine_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_ReportLine_Definition;
	using COBOL_Report_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Report_Definition;
	using COBOL_Keyword = com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
	using COBOL_Level = com.eagle.programmar.COBOL.Terminals.COBOL_Level;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class COBOL_ReportEntry : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) COBOL_ReportDescription rd;
		public COBOL_ReportDescription rd;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<COBOL_ReportDataLine> dataLines;
		public TokenList<COBOL_ReportDataLine> dataLines;

		public class COBOL_ReportDescription : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword RD = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("RD");
			public COBOL_Keyword RD = new COBOL_Keyword("RD");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Symbols.COBOL_Report_Definition reportName;
			public COBOL_Report_Definition reportName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<COBOL_ReportDescriptionEntry> descriptionEntries;
			public TokenList<COBOL_ReportDescriptionEntry> descriptionEntries;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
		}

		public class COBOL_ReportDataLine : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Level O1;
			public COBOL_Level O1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT COBOL_ReportLine_Definition id;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword TYPE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("TYPE");
			public COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) COBOL_ReportDataLineType lineType;
			public COBOL_ReportDataLineType lineType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT COBOL_ReportNextGroup next;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.TokenList<COBOL_ReportLine> reportLines;
			public TokenList<COBOL_ReportLine> reportLines;
		}

		public class COBOL_ReportNextGroup : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword NEXT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("NEXT");
			public COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword GROUP = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("GROUP");
			public COBOL_Keyword GROUP = new COBOL_Keyword("GROUP");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PLUS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PLUS");
			public COBOL_Keyword PLUS = new COBOL_Keyword("PLUS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
		}
	}

}
