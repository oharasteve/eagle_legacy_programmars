// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.CMacro.Expressions
{
	using CMacro_Expression = com.eagle.programmar.CMacro.CMacro_Expression;
	using CMacro_Punctuation = com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class CMacro_SymbolExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation poundOperator = new com.eagle.programmar.CMacro.Terminals.CMacro_Punctuation('#');
		public CMacro_Punctuation poundOperator = new CMacro_Punctuation('#');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CMacro.CMacro_Expression expr;
		public CMacro_Expression expr;
	}

}
