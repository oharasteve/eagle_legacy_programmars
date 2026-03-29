// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Directives
{
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_Space_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword SPACE = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(7, 12, "/SPACE");
		public RPG_Keyword SPACE = new RPG_Keyword(7, 12, "/SPACE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Number lines = new com.eagle.programmar.RPG.Terminals.RPG_Number(14, 16);
		public RPG_Number lines = new RPG_Number(14, 16);
	}

}
