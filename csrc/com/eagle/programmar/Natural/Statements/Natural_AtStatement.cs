// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_AtStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Natural.Terminals.Natural_Keyword AT = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("AT");
		public Natural_Keyword AT = new Natural_Keyword("AT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Natural_AtWhat atWhat;
		public Natural_AtWhat atWhat;

		public class Natural_AtWhat : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_AtEndOfData extends com.eagle.tokens.TokenSequence
			public class Natural_AtEndOfData : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/atenddat.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword END = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END");
				public @DOC("sm/atenddat.htm") Natural_Keyword END = new Natural_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword OF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("OF");
				public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword DATA = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DATA");
				public Natural_Keyword DATA = new Natural_Keyword("DATA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
				public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Natural_Keyword ENDENDDATA = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-ENDDATA");
				public @OPT Natural_Keyword ENDENDDATA = new Natural_Keyword("END-ENDDATA");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_AtEndOfPage extends com.eagle.tokens.TokenSequence
			public static class Natural_AtEndOfPage extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/atendpag.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword END = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END");
				public @DOC("sm/atendpag.htm") Natural_Keyword END = new Natural_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword OF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("OF");
				public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword PAGE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("PAGE");
				public Natural_Keyword PAGE = new Natural_Keyword("PAGE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
				public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDENDPAGE = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-ENDPAGE");
				public Natural_Keyword ENDENDPAGE = new Natural_Keyword("END-ENDPAGE");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_AtBreakOfVariable extends com.eagle.tokens.TokenSequence
			public static class Natural_AtBreakOfVariable extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/atbreak.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword BREAK = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("BREAK");
				public @DOC("sm/atbreak.htm") Natural_Keyword BREAK = new Natural_Keyword("BREAK");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword OF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("OF");
				public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Variable var;
				public Natural_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
				public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDBREAK = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-BREAK");
				public Natural_Keyword ENDBREAK = new Natural_Keyword("END-BREAK");
			}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Natural_AtStartOfData extends com.eagle.tokens.TokenSequence
			public static class Natural_AtStartOfData extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/atstart.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword START = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("START");
				public @DOC("sm/atstart.htm") Natural_Keyword START = new Natural_Keyword("START");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Natural_Keyword OF = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("OF");
				public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Terminals.Natural_Keyword DATA = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("DATA");
				public Natural_Keyword DATA = new Natural_Keyword("DATA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
				public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDSTART = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-START");
				public Natural_Keyword ENDSTART = new Natural_Keyword("END-START");
			}
		}
	}

}
