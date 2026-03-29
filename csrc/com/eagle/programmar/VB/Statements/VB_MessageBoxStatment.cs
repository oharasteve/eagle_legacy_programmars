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
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class VB_MessageBoxStatment : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.Terminals.VB_Keyword MESSAGEBOX = new com.eagle.programmar.VB.Terminals.VB_Keyword("MsgBox");
		public VB_Keyword MESSAGEBOX = new VB_Keyword("MsgBox");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.VB_Expression expr;
		public VB_Expression expr;
	}

}
