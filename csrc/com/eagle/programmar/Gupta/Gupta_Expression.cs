// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

namespace com.eagle.programmar.Gupta
{
	using Gupta_Additive_Expression = com.eagle.programmar.Gupta.Expressions.Gupta_Additive_Expression;
	using Gupta_FunctionCall = com.eagle.programmar.Gupta.Expressions.Gupta_FunctionCall;
	using Gupta_IdentifierExpression = com.eagle.programmar.Gupta.Expressions.Gupta_IdentifierExpression;
	using Gupta_Multiplicative_Expression = com.eagle.programmar.Gupta.Expressions.Gupta_Multiplicative_Expression;
	using Gupta_Parens = com.eagle.programmar.Gupta.Expressions.Gupta_Parens;
	using Gupta_StrCat_Expression = com.eagle.programmar.Gupta.Expressions.Gupta_StrCat_Expression;
	using Gupta_UnarySign = com.eagle.programmar.Gupta.Expressions.Gupta_UnarySign;
	using Gupta_Literal = com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
	using Gupta_Number = com.eagle.programmar.Gupta.Terminals.Gupta_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Gupta_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public Gupta_Expression() : base(_operators)
		{
		}

		public Gupta_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Gupta.Terminals.Gupta_Number number;
		public Gupta_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Gupta.Terminals.Gupta_Literal literal;
		public Gupta_Literal literal;

		///////////////////////////////////////////////////////////////////////////
		// Primary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Gupta.Expressions.Gupta_Parens parens;
		public Gupta_Parens parens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Gupta.Expressions.Gupta_FunctionCall functionCall;
		public Gupta_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Gupta.Expressions.Gupta_IdentifierExpression identifierExpression;
		public Gupta_IdentifierExpression identifierExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Gupta.Expressions.Gupta_UnarySign unarySign;
		public Gupta_UnarySign unarySign;

		///////////////////////////////////////////////////////////////////////////
		// Binary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Gupta.Expressions.Gupta_Multiplicative_Expression multiplicative_Expression;
		public Gupta_Multiplicative_Expression multiplicative_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Gupta.Expressions.Gupta_Additive_Expression additive_Expression;
		public Gupta_Additive_Expression additive_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Gupta.Expressions.Gupta_StrCat_Expression strCat_Expression;
		public Gupta_StrCat_Expression strCat_Expression;
	}

}
