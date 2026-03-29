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
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Python_StarStar_Expression : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Punctuation starStar = new com.eagle.programmar.Python.Terminals.Python_Punctuation("**");
		public Python_Punctuation starStar = new Python_Punctuation("**");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Python_Expression expr;
		public Python_Expression expr;
	}

}
