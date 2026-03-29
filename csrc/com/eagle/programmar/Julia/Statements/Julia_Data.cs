// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia.Statements
{
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_Variable_Definition = com.eagle.programmar.Julia.Symbols.Julia_Variable_Definition;
	using Julia_EOLN = com.eagle.programmar.Julia.Terminals.Julia_EOLN;
	using Julia_Keyword = com.eagle.programmar.Julia.Terminals.Julia_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Julia_Data : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Terminals.Julia_Keyword VAR = new com.eagle.programmar.Julia.Terminals.Julia_Keyword("var");
		public Julia_Keyword VAR = new Julia_Keyword("var");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Symbols.Julia_Variable_Definition id;
		public Julia_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Julia.Julia_Expression value;
		public Julia_Expression value;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Julia.Terminals.Julia_EOLN eoln;
		public Julia_EOLN eoln;
	}

}
