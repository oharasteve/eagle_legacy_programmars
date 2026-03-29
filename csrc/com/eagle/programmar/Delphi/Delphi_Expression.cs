// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi
{
	using Delphi_Additive_Expression = com.eagle.programmar.Delphi.Expressions.Delphi_Additive_Expression;
	using Delphi_Brackets = com.eagle.programmar.Delphi.Expressions.Delphi_Brackets;
	using Delphi_Builtins = com.eagle.programmar.Delphi.Expressions.Delphi_Builtins;
	using Delphi_Cast = com.eagle.programmar.Delphi.Expressions.Delphi_Cast;
	using Delphi_DotDot_Expression = com.eagle.programmar.Delphi.Expressions.Delphi_DotDot_Expression;
	using Delphi_Dot_Expression = com.eagle.programmar.Delphi.Expressions.Delphi_Dot_Expression;
	using Delphi_Function_Call = com.eagle.programmar.Delphi.Expressions.Delphi_Function_Call;
	using Delphi_Multiplicative_Expression = com.eagle.programmar.Delphi.Expressions.Delphi_Multiplicative_Expression;
	using Delphi_Not_Operator = com.eagle.programmar.Delphi.Expressions.Delphi_Not_Operator;
	using Delphi_Parentheses = com.eagle.programmar.Delphi.Expressions.Delphi_Parentheses;
	using Delphi_Relational_Expression = com.eagle.programmar.Delphi.Expressions.Delphi_Relational_Expression;
	using Delphi_UnarySign = com.eagle.programmar.Delphi.Expressions.Delphi_UnarySign;
	using Delphi_Variable_Expression = com.eagle.programmar.Delphi.Expressions.Delphi_Variable_Expression;
	using Delphi_Abs_Function = com.eagle.programmar.Delphi.Functions.Delphi_Abs_Function;
	using Delphi_Copy_Function = com.eagle.programmar.Delphi.Functions.Delphi_Copy_Function;
	using Delphi_Format_Function = com.eagle.programmar.Delphi.Functions.Delphi_Format_Function;
	using Delphi_Length_Function = com.eagle.programmar.Delphi.Functions.Delphi_Length_Function;
	using Delphi_Odd_Function = com.eagle.programmar.Delphi.Functions.Delphi_Odd_Function;
	using Delphi_Pred_Function = com.eagle.programmar.Delphi.Functions.Delphi_Pred_Function;
	using Delphi_Succ_Function = com.eagle.programmar.Delphi.Functions.Delphi_Succ_Function;
	using Delphi_Character = com.eagle.programmar.Delphi.Terminals.Delphi_Character;
	using Delphi_HexNumber = com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber;
	using Delphi_Literal = com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
	using Delphi_Number = com.eagle.programmar.Delphi.Terminals.Delphi_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Delphi_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Delphi_Expression() : base(_operators)
		{
		}

		public Delphi_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Delphi.Terminals.Delphi_Number number;
		public Delphi_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber hex;
		public Delphi_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Delphi.Terminals.Delphi_Literal literal;
		public Delphi_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.Delphi.Terminals.Delphi_Character character;
		public Delphi_Character character;

		///////////////////////////////////////////////////////////////////////////
		// Primary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Delphi.Expressions.Delphi_Builtins builtins;
		public Delphi_Builtins builtins;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Delphi.Expressions.Delphi_Parentheses parens;
		public Delphi_Parentheses parens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Delphi.Expressions.Delphi_Brackets brackets;
		public Delphi_Brackets brackets;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Delphi.Functions.Delphi_Copy_Function copyFunction;
		public Delphi_Copy_Function copyFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Delphi.Functions.Delphi_Format_Function formatFunction;
		public Delphi_Format_Function formatFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Delphi.Functions.Delphi_Length_Function lengthFunction;
		public Delphi_Length_Function lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Delphi.Functions.Delphi_Odd_Function oddFunction;
		public Delphi_Odd_Function oddFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Delphi.Functions.Delphi_Abs_Function absFunction;
		public Delphi_Abs_Function absFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Delphi.Functions.Delphi_Pred_Function predFunction;
		public Delphi_Pred_Function predFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Delphi.Functions.Delphi_Succ_Function succFunction;
		public Delphi_Succ_Function succFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Delphi.Expressions.Delphi_Function_Call functionCall;
		public Delphi_Function_Call functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Delphi.Expressions.Delphi_Cast cast;
		public Delphi_Cast cast;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Delphi.Expressions.Delphi_Variable_Expression variableExpression;
		public Delphi_Variable_Expression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Delphi.Expressions.Delphi_UnarySign unarySign;
		public Delphi_UnarySign unarySign;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.Delphi.Expressions.Delphi_Not_Operator notOp;
		public Delphi_Not_Operator notOp;

		///////////////////////////////////////////////////////////////////////////
		// Binary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Delphi.Expressions.Delphi_Dot_Expression dotExpression;
		public Delphi_Dot_Expression dotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Delphi.Expressions.Delphi_Multiplicative_Expression multiplicativeExpression;
		public Delphi_Multiplicative_Expression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Delphi.Expressions.Delphi_Additive_Expression additiveExpression;
		public Delphi_Additive_Expression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Delphi.Expressions.Delphi_Relational_Expression relationalExpression;
		public Delphi_Relational_Expression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Delphi.Expressions.Delphi_DotDot_Expression dotDotExpression;
		public Delphi_DotDot_Expression dotDotExpression;
	}

}
