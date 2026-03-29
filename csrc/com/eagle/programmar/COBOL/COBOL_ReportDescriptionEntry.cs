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
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class COBOL_ReportDescriptionEntry : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDescriptionControls extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDescriptionControls : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword CONTROLS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("CONTROLS");
			public COBOL_Keyword CONTROLS = new COBOL_Keyword("CONTROLS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword ARE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("ARE");
			public COBOL_Keyword ARE = new COBOL_Keyword("ARE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT COBOL_Keyword FINAL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FINAL");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference> ids;
			public TokenList<COBOL_Identifier_Reference> ids;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDescriptionPageLimit extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDescriptionPageLimit : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword PAGE = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("PAGE");
			public COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LIMIT = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LIMIT");
			public COBOL_Keyword LIMIT = new COBOL_Keyword("LIMIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword IS = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("IS");
			public COBOL_Keyword IS = new COBOL_Keyword("IS");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDescriptionHeading extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDescriptionHeading : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword HEADING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("HEADING");
			public COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDescriptionFirstDetail extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDescriptionFirstDetail : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FIRST = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FIRST");
			public COBOL_Keyword FIRST = new COBOL_Keyword("FIRST");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DETAIL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DETAIL");
			public COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDescriptionLastDetail extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDescriptionLastDetail : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword LAST = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("LAST");
			public COBOL_Keyword LAST = new COBOL_Keyword("LAST");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword DETAIL = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("DETAIL");
			public COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class COBOL_ReportDescriptionFooting extends com.eagle.tokens.TokenSequence
		public class COBOL_ReportDescriptionFooting : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.COBOL.Terminals.COBOL_Keyword FOOTING = new com.eagle.programmar.COBOL.Terminals.COBOL_Keyword("FOOTING");
			public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.COBOL.Terminals.COBOL_Number count;
			public COBOL_Number count;
		}
	}
}
