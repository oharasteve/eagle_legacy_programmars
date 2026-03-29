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
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_Copy_Directive : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword COPY = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(7, 112, "/COPY");
		public RPG_Keyword COPY = new RPG_Keyword(7, 112, "/COPY");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal filename = new com.eagle.programmar.RPG.Terminals.RPG_Literal(13, 44);
		public RPG_Literal filename = new RPG_Literal(13, 44);
	}

}
