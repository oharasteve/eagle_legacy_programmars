// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.PLI.Expressions
{
	using PLI_Expression = com.eagle.programmar.PLI.PLI_Expression;
	using PLI_Punctuation = com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class PLI_LogicalAndThenExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.PLI_Expression left = new com.eagle.programmar.PLI.PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Punctuation andThen1 = new com.eagle.programmar.PLI.Terminals.PLI_Punctuation("&:");
		public PLI_Punctuation andThen1 = new PLI_Punctuation("&:");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.PLI_Expression right = new com.eagle.programmar.PLI.PLI_Expression(this, AllowedPrecedence.HIGHER);
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);
	}

}
