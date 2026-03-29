// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

namespace com.eagle.programmar.Natural.Statements
{
	using Natural_Statement = com.eagle.programmar.Natural.Natural_Statement;
	using Natural_Variable = com.eagle.programmar.Natural.Natural_Variable;
	using Natural_Identifier_Reference = com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
	using Natural_Keyword = com.eagle.programmar.Natural.Terminals.Natural_Keyword;
	using Natural_Literal = com.eagle.programmar.Natural.Terminals.Natural_Literal;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Natural_HistogramStatement : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sm/histogra.htm") com.eagle.programmar.Natural.Terminals.Natural_Keyword HISTOGRAM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("HISTOGRAM");
		public @DOC("sm/histogra.htm") Natural_Keyword HISTOGRAM = new Natural_Keyword("HISTOGRAM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference viewName;
		public Natural_Identifier_Reference viewName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Natural.Natural_Variable var;
		public Natural_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Natural.Terminals.Natural_Keyword STARTING = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("STARTING");
		public Natural_Keyword STARTING = new Natural_Keyword("STARTING");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Natural.Terminals.Natural_Keyword FROM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("FROM");
		public Natural_Keyword FROM = new Natural_Keyword("FROM");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Natural.Terminals.Natural_Literal literal;
		public Natural_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.TokenList<com.eagle.programmar.Natural.Natural_Statement> statements;
		public TokenList<Natural_Statement> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.Natural.Terminals.Natural_Keyword ENDHISTOGRAM = new com.eagle.programmar.Natural.Terminals.Natural_Keyword("END-HISTOGRAM");
		public Natural_Keyword ENDHISTOGRAM = new Natural_Keyword("END-HISTOGRAM");
	}

}
