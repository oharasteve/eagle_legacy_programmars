// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

namespace com.eagle.programmar.Django.Controls
{
	using Django_Expression = com.eagle.programmar.Django.Django_Expression;
	using Django_Variable_Definition = com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class Django_ImportControl : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Terminals.Django_Keyword IMPORT = new com.eagle.programmar.Django.Terminals.Django_Keyword("import");
		public Django_Keyword IMPORT = new Django_Keyword("import");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Django_Expression module;
		public Django_Expression module;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Django.Terminals.Django_Keyword AS = new com.eagle.programmar.Django.Terminals.Django_Keyword("as");
		public Django_Keyword AS = new Django_Keyword("as");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Django.Symbols.Django_Variable_Definition var;
		public Django_Variable_Definition var;
	}

}
