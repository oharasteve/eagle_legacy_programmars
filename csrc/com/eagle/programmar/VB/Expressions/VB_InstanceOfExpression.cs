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
	using VB_Type = com.eagle.programmar.VB.VB_Type;
	using VB_Keyword = com.eagle.programmar.VB.Terminals.VB_Keyword;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class VB_InstanceOfExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.VB.VB_Expression expr = new com.eagle.programmar.VB.VB_Expression(this, AllowedPrecedence.ATLEAST);
		public VB_Expression expr = new VB_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.VB.Terminals.VB_Keyword instanceOperator = new com.eagle.programmar.VB.Terminals.VB_Keyword("instanceof");
		public VB_Keyword instanceOperator = new VB_Keyword("instanceof");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.VB.VB_Type type;
		public VB_Type type;
	}

}
