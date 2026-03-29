// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

namespace com.eagle.programmar.Ada.Expressions
{
	using Ada_Expression = com.eagle.programmar.Ada.Ada_Expression;
	using Ada_PunctuationChoice = com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;

	public class Ada_AssignmentExpression : PrecedenceOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Ada.Ada_Expression var = new com.eagle.programmar.Ada.Ada_Expression(this, AllowedPrecedence.HIGHER);
		public Ada_Expression var = new Ada_Expression(this, AllowedPrecedence.HIGHER);
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice equals = new com.eagle.programmar.Ada.Terminals.Ada_PunctuationChoice(":=", "*=", "+=", "-=");
		public Ada_PunctuationChoice equals = new Ada_PunctuationChoice(":=", "*=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Ada.Ada_Expression expr;
		public Ada_Expression expr;
	}

}
