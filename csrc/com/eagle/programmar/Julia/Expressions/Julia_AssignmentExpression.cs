// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Julia.Expressions
{
	using Julia_Expression = com.eagle.programmar.Julia.Julia_Expression;
	using Julia_PunctuationChoice = com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Julia_AssignmentExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Julia.Julia_Expression var = new com.eagle.programmar.Julia.Julia_Expression(this, AllowedPrecedence.HIGHER);
		public Julia_Expression var = new Julia_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice equals = new com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice(":=", "*=", "/=", "%=", "+=", "-=");
		public Julia_PunctuationChoice equals = new Julia_PunctuationChoice(":=", "*=", "/=", "%=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Julia.Julia_Expression expr;
		public Julia_Expression expr;
	}

}
