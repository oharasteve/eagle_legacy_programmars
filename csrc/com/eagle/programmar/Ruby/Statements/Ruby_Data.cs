// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby.Statements
{
	using Ruby_Expression = com.eagle.programmar.Ruby.Ruby_Expression;
	using Ruby_Variable_Definition = com.eagle.programmar.Ruby.Symbols.Ruby_Variable_Definition;
	using Ruby_EOLN = com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
	using Ruby_Keyword = com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Ruby_Data : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ruby.Terminals.Ruby_Keyword VAR = new com.eagle.programmar.Ruby.Terminals.Ruby_Keyword("var");
		public Ruby_Keyword VAR = new Ruby_Keyword("var");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ruby.Symbols.Ruby_Variable_Definition id;
		public Ruby_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Ruby.Ruby_Expression value;
		public Ruby_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Ruby.Terminals.Ruby_EOLN eoln;
		public Ruby_EOLN eoln;
	}

}
