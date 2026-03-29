// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Django.Expressions
{
	using Django_Variable = com.eagle.programmar.Django.Django_Variable;
	using Django_Keyword = com.eagle.programmar.Django.Terminals.Django_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Django_DefinedExpression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Django.Django_Variable variable;
		public Django_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Django.Terminals.Django_Keyword IS = new com.eagle.programmar.Django.Terminals.Django_Keyword("is");
		public Django_Keyword IS = new Django_Keyword("is");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Django_Keyword NOT = new com.eagle.programmar.Django.Terminals.Django_Keyword("not");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Django.Terminals.Django_Keyword DEFINED = new com.eagle.programmar.Django.Terminals.Django_Keyword("defined");
		public Django_Keyword DEFINED = new Django_Keyword("defined");
	}

}
