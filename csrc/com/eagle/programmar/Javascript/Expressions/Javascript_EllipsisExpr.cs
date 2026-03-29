// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Javascript.Expressions
{
	using Javascript_Expression = com.eagle.programmar.Javascript.Javascript_Expression;
	using Javascript_Punctuation = com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Javascript_EllipsisExpr : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation ellipsis = new com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation("...");
		public Javascript_Punctuation ellipsis = new Javascript_Punctuation("...");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Javascript.Javascript_Expression expr;
		public Javascript_Expression expr;
	}

}
