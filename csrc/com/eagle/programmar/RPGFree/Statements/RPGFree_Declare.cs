// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree.Statements
{
	using RPGFree_Type = com.eagle.programmar.RPGFree.RPGFree_Type;
	using RPGFree_Variable_Definition = com.eagle.programmar.RPGFree.Symbols.RPGFree_Variable_Definition;
	using RPGFree_Keyword = com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class RPGFree_Declare : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword DCL = new com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword("dcl-s");
		public RPGFree_Keyword DCL = new RPGFree_Keyword("dcl-s");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.RPGFree.Symbols.RPGFree_Variable_Definition variable;
		public RPGFree_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.RPGFree.RPGFree_Type type;
		public RPGFree_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;
	}

}
