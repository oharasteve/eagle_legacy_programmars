// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 16, 2014

namespace com.eagle.programmar.Django.Controls
{
	using Django_Variable_Definition = com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Django_LoadControl : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword LOAD = new com.eagle.programmar.Django.Terminals.Django_Keyword("load");
		public Django_Keyword LOAD = new Django_Keyword("load");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.TokenList<com.eagle.programmar.Django.Symbols.Django_Variable_Definition> variables;
		public TokenList<Django_Variable_Definition> variables;
	}

}
