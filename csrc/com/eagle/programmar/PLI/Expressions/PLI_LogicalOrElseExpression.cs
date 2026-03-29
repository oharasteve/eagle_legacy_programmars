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
	using PLI_PunctuationChoice = com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class PLI_LogicalOrElseExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.PLI_Expression left = new com.eagle.programmar.PLI.PLI_Expression(this, AllowedPrecedence.ATLEAST);
		public PLI_Expression left = new PLI_Expression(this, AllowedPrecedence.ATLEAST);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PLI_OrElseOperator orElseOper;
		public PLI_OrElseOperator orElseOper;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.PLI_Expression right = new com.eagle.programmar.PLI.PLI_Expression(this, AllowedPrecedence.HIGHER);
		public PLI_Expression right = new PLI_Expression(this, AllowedPrecedence.HIGHER);

		public class PLI_OrElseOperator : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_PunctuationChoice XXorElse = new com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice("!:", "|:");
			public PLI_PunctuationChoice XXorElse = new PLI_PunctuationChoice("!:", "|:");
		}
	}

}
