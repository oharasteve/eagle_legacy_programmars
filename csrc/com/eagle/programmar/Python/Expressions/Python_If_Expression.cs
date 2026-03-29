// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{
	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Python_If_Expression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Python_Expression left = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.ATLEAST);
		public Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_Keyword IF = new com.eagle.programmar.Python.Terminals.Python_Keyword("if");
		public Python_Keyword IF = new Python_Keyword("if");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Python.Python_Expression middle = new com.eagle.programmar.Python.Python_Expression(this, AllowedPrecedence.HIGHER);
		public Python_Expression middle = new Python_Expression(this, AllowedPrecedence.HIGHER);
	}
}
