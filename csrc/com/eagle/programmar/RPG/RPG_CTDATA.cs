// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPG
{
	using RPG_EndOfLine = com.eagle.programmar.RPG.Terminals.RPG_EndOfLine;
	using RPG_Keyword = com.eagle.programmar.RPG.Terminals.RPG_Keyword;
	using RPG_Literal = com.eagle.programmar.RPG.Terminals.RPG_Literal;
	using RPG_Number = com.eagle.programmar.RPG.Terminals.RPG_Number;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class RPG_CTDATA : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Keyword CTDATA = new com.eagle.programmar.RPG.Terminals.RPG_Keyword(1, 9, "** CTDATA");
		public RPG_Keyword CTDATA = new RPG_Keyword(1, 9, "** CTDATA");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_Literal array = new com.eagle.programmar.RPG.Terminals.RPG_Literal(11, 16);
		public RPG_Literal array = new RPG_Literal(11, 16);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPG.Terminals.RPG_EndOfLine eoln;
		public RPG_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.TokenList<RPG_CTDATA_item> items;
		public TokenList<RPG_CTDATA_item> items;

		public class RPG_CTDATA_item : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPG.Terminals.RPG_Number number = new com.eagle.programmar.RPG.Terminals.RPG_Number(1, 10);
			public RPG_Number number = new RPG_Number(1, 10);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPG.Terminals.RPG_EndOfLine eoln;
			public RPG_EndOfLine eoln;
		}
	}

}
