// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Expressions
{
	using SQL_Number = com.eagle.programmar.SQL.Terminals.SQL_Number;
	using SQL_Punctuation = com.eagle.programmar.SQL.Terminals.SQL_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class SQL_DollarVariable : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Punctuation dollar = new com.eagle.programmar.SQL.Terminals.SQL_Punctuation('$');
		public SQL_Punctuation dollar = new SQL_Punctuation('$');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Number number;
		public SQL_Number number;
	}

}
