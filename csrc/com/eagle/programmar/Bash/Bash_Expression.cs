// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Bash
{
	using Bash_AdditiveExpression = com.eagle.programmar.Bash.Expressions.Bash_AdditiveExpression;
	using Bash_Array = com.eagle.programmar.Bash.Expressions.Bash_Array;
	using Bash_Assignment_Expression = com.eagle.programmar.Bash.Expressions.Bash_Assignment_Expression;
	using Bash_DollarExpr = com.eagle.programmar.Bash.Expressions.Bash_DollarExpr;
	using Bash_DollarNumber = com.eagle.programmar.Bash.Expressions.Bash_DollarNumber;
	using Bash_DollarPound = com.eagle.programmar.Bash.Expressions.Bash_DollarPound;
	using Bash_DollarSubstring = com.eagle.programmar.Bash.Expressions.Bash_DollarSubstring;
	using Bash_Evaluate1 = com.eagle.programmar.Bash.Expressions.Bash_Evaluate1;
	using Bash_Evaluate2 = com.eagle.programmar.Bash.Expressions.Bash_Evaluate2;
	using Bash_LogicalAnd_Expression = com.eagle.programmar.Bash.Expressions.Bash_LogicalAnd_Expression;
	using Bash_LogicalNotExpression = com.eagle.programmar.Bash.Expressions.Bash_LogicalNotExpression;
	using Bash_LogicalOr_Expression = com.eagle.programmar.Bash.Expressions.Bash_LogicalOr_Expression;
	using Bash_MultiplicativeExpression = com.eagle.programmar.Bash.Expressions.Bash_MultiplicativeExpression;
	using Bash_NegativeExpression = com.eagle.programmar.Bash.Expressions.Bash_NegativeExpression;
	using Bash_ParenthesizedExpression = com.eagle.programmar.Bash.Expressions.Bash_ParenthesizedExpression;
	using Bash_RangeExpression = com.eagle.programmar.Bash.Expressions.Bash_RangeExpression;
	using Bash_Relational_Expression = com.eagle.programmar.Bash.Expressions.Bash_Relational_Expression;
	using Bash_SizeExpression = com.eagle.programmar.Bash.Expressions.Bash_SizeExpression;
	using Bash_VariableExpression = com.eagle.programmar.Bash.Expressions.Bash_VariableExpression;
	using Bash_Literal = com.eagle.programmar.Bash.Terminals.Bash_Literal;
	using Bash_Number = com.eagle.programmar.Bash.Terminals.Bash_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Bash_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public Bash_Expression() : base(_operators)
		{
		}

		public Bash_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Bash.Terminals.Bash_Number number;
		public Bash_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Bash.Terminals.Bash_Literal literal;
		public Bash_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Bash.Expressions.Bash_DollarNumber dollarNumber;
		public Bash_DollarNumber dollarNumber;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Bash.Expressions.Bash_DollarPound dollarPound;
		public Bash_DollarPound dollarPound;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Bash.Expressions.Bash_DollarExpr dollarExpr;
		public Bash_DollarExpr dollarExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Bash.Expressions.Bash_DollarSubstring dollarSubstring;
		public Bash_DollarSubstring dollarSubstring;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Bash.Expressions.Bash_SizeExpression sizeExpression;
		public Bash_SizeExpression sizeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Bash.Expressions.Bash_ParenthesizedExpression parensExpression;
		public Bash_ParenthesizedExpression parensExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Bash.Expressions.Bash_NegativeExpression negativeExpression;
		public Bash_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Bash.Expressions.Bash_LogicalNotExpression notExpression;
		public Bash_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Bash.Expressions.Bash_VariableExpression variableExpression;
		public Bash_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Bash.Expressions.Bash_Array array;
		public Bash_Array array;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Bash.Expressions.Bash_Evaluate1 evaluate1;
		public Bash_Evaluate1 evaluate1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Bash.Expressions.Bash_Evaluate2 evaluate2;
		public Bash_Evaluate2 evaluate2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Bash.Expressions.Bash_RangeExpression range;
		public Bash_RangeExpression range;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Bash.Expressions.Bash_MultiplicativeExpression multiplicativeExpression;
		public Bash_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Bash.Expressions.Bash_AdditiveExpression additiveExpression;
		public Bash_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Bash.Expressions.Bash_Relational_Expression relational_Expression;
		public Bash_Relational_Expression relational_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Bash.Expressions.Bash_LogicalAnd_Expression logicalAnd_Expression;
		public Bash_LogicalAnd_Expression logicalAnd_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Bash.Expressions.Bash_LogicalOr_Expression logicalOr_Expression;
		public Bash_LogicalOr_Expression logicalOr_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Bash.Expressions.Bash_Assignment_Expression assignment_Expression;
		public Bash_Assignment_Expression assignment_Expression;
	}

}
