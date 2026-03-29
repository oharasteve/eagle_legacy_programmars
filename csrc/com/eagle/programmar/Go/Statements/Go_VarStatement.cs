// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

namespace com.eagle.programmar.Go.Statements
{
	using Go_Type = com.eagle.programmar.Go.Go_Type;
	using Go_Variable_Definition = com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
	using Go_EOLN = com.eagle.programmar.Go.Terminals.Go_EOLN;
	using Go_Keyword = com.eagle.programmar.Go.Terminals.Go_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Go_VarStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Go.Terminals.Go_Keyword VAR = new com.eagle.programmar.Go.Terminals.Go_Keyword("var");
		public Go_Keyword VAR = new Go_Keyword("var");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Go.Symbols.Go_Variable_Definition variable;
		public Go_Variable_Definition variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Go.Go_Type type;
		public Go_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Go.Terminals.Go_EOLN eoln;
		public Go_EOLN eoln;
	}

}
