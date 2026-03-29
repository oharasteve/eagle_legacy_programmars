// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL.Statements
{
	using TCL_Variable_Definition = com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class TCL_VariableStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("TclCmd/variable.html") com.eagle.programmar.TCL.Terminals.TCL_Keyword VARIABLE = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("variable");
		public @DOC("TclCmd/variable.html") TCL_Keyword VARIABLE = new TCL_Keyword("variable");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition variable;
		public TCL_Variable_Definition variable;
	}

}
