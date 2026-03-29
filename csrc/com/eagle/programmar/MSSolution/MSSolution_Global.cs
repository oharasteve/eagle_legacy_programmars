// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution
{
	using MSSolution_EndOfLine = com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine;
	using MSSolution_Keyword = com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class MSSolution_Global : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword GLOBAL = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("Global");
		public MSSolution_Keyword GLOBAL = new MSSolution_Keyword("Global");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln1;
		public MSSolution_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<MSSolution_GlobalSection> sections;
		public TokenList<MSSolution_GlobalSection> sections;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword ENDGLOBAL = new com.eagle.programmar.MSSolution.Terminals.MSSolution_Keyword("EndGlobal");
		public MSSolution_Keyword ENDGLOBAL = new MSSolution_Keyword("EndGlobal");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.MSSolution.Terminals.MSSolution_EndOfLine eoln2;
		public MSSolution_EndOfLine eoln2;
	}

}
