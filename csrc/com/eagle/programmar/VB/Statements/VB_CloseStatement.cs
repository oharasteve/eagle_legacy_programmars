// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

namespace com.eagle.programmar.VB.Statements
{
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using VB_Number = com.eagle.programmar.VB.Terminals.VB_Number;
	using VB_Punctuation = com.eagle.programmar.VB.Terminals.VB_Punctuation;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_CloseStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword CLOSE = new com.eagle.programmar.VB.Terminals.VB_Keyword("close");
		public VB_Keyword CLOSE = new VB_Keyword("close");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Punctuation pound = new com.eagle.programmar.VB.Terminals.VB_Punctuation('#');
		public VB_Punctuation pound = new VB_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.Terminals.VB_Number channel;
		public VB_Number channel;
	}

}
