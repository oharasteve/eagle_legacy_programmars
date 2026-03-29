// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

namespace com.eagle.programmar.VB.Statements
{
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_KeywordChoice = com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
	using VB_Number = com.eagle.programmar.VB.Terminals.VB_Number;
	using VB_Punctuation = com.eagle.programmar.VB.Terminals.VB_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_OpenStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword OPEN = new com.eagle.programmar.VB.Terminals.VB_Keyword("open");
		public VB_Keyword OPEN = new VB_Keyword("open");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression fileName;
		public VB_Expression fileName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_Keyword FOR = new com.eagle.programmar.VB.Terminals.VB_Keyword("for");
		public VB_Keyword FOR = new VB_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.VB.Terminals.VB_KeywordChoice inputOutput = new com.eagle.programmar.VB.Terminals.VB_KeywordChoice("input", "output");
		public VB_KeywordChoice inputOutput = new VB_KeywordChoice("input", "output");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.VB.Terminals.VB_Keyword AS = new com.eagle.programmar.VB.Terminals.VB_Keyword("as");
		public VB_Keyword AS = new VB_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.VB.Terminals.VB_Punctuation pound = new com.eagle.programmar.VB.Terminals.VB_Punctuation('#');
		public VB_Punctuation pound = new VB_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.VB.Terminals.VB_Number channel;
		public VB_Number channel;
	}

}
