// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.VB.Expressions
{
	using VB_Expression = com.eagle.programmar.VB.VB_Expression;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class VB_LogicalXorExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.VB_Expression left = new com.eagle.programmar.VB.VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("operators/logical-bitwise-operators") com.eagle.programmar.VB.Terminals.VB_Keyword xor = new com.eagle.programmar.VB.Terminals.VB_Keyword("xor");
		public @DOC("operators/logical-bitwise-operators") VB_Keyword xor = new VB_Keyword("xor");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.VB_Expression right = new com.eagle.programmar.VB.VB_Expression(this, AllowedPrecedence.HIGHER);
		public VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
	}

}
