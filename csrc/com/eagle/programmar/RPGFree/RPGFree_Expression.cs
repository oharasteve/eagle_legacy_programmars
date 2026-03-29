// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree
{
	using RPGFree_Additive = com.eagle.programmar.RPGFree.Expressions.RPGFree_Additive;
	using RPGFree_Multiplicative = com.eagle.programmar.RPGFree.Expressions.RPGFree_Multiplicative;
	using RPGFree_Parentheses = com.eagle.programmar.RPGFree.Expressions.RPGFree_Parentheses;
	using RPGFree_VariableExpression = com.eagle.programmar.RPGFree.Expressions.RPGFree_VariableExpression;
	using RPGFree_CharFunction = com.eagle.programmar.RPGFree.Functions.RPGFree_CharFunction;
	using RPGFree_Literal = com.eagle.programmar.RPGFree.Terminals.RPGFree_Literal;
	using RPGFree_Number = com.eagle.programmar.RPGFree.Terminals.RPGFree_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class RPGFree_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public RPGFree_Expression() : base(_operators)
		{
		}

		public RPGFree_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.RPGFree.Terminals.RPGFree_Number number;
		public RPGFree_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.RPGFree.Terminals.RPGFree_Literal literal;
		public RPGFree_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.RPGFree.Functions.RPGFree_CharFunction charFunction;
		public RPGFree_CharFunction charFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.RPGFree.Expressions.RPGFree_VariableExpression var;
		public RPGFree_VariableExpression var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.RPGFree.Expressions.RPGFree_Parentheses parens;
		public RPGFree_Parentheses parens;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.RPGFree.Expressions.RPGFree_Multiplicative multiplicative;
		public RPGFree_Multiplicative multiplicative;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.RPGFree.Expressions.RPGFree_Additive additive;
		public RPGFree_Additive additive;
	}

}
