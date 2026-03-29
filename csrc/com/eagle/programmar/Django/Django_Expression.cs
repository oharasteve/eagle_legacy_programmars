// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

namespace com.eagle.programmar.Django
{
	using Django_AndExpression = com.eagle.programmar.Django.Expressions.Django_AndExpression;
	using Django_BarExpression = com.eagle.programmar.Django.Expressions.Django_BarExpression;
	using Django_BracketsExpression = com.eagle.programmar.Django.Expressions.Django_BracketsExpression;
	using Django_DefinedExpression = com.eagle.programmar.Django.Expressions.Django_DefinedExpression;
	using Django_EqualityExpression = com.eagle.programmar.Django.Expressions.Django_EqualityExpression;
	using Django_ExpressionRange = com.eagle.programmar.Django.Expressions.Django_ExpressionRange;
	using Django_FormatExpression = com.eagle.programmar.Django.Expressions.Django_FormatExpression;
	using Django_FunctionExpression = com.eagle.programmar.Django.Expressions.Django_FunctionExpression;
	using Django_InExpression = com.eagle.programmar.Django.Expressions.Django_InExpression;
	using Django_NotExpression = com.eagle.programmar.Django.Expressions.Django_NotExpression;
	using Django_OrExpression = com.eagle.programmar.Django.Expressions.Django_OrExpression;
	using Django_ParensExpression = com.eagle.programmar.Django.Expressions.Django_ParensExpression;
	using Django_SubscriptExpression = com.eagle.programmar.Django.Expressions.Django_SubscriptExpression;
	using Django_VariableExpression = com.eagle.programmar.Django.Expressions.Django_VariableExpression;
	using Django_Literal = com.eagle.programmar.Django.Terminals.Django_Literal;
	using Django_Number = com.eagle.programmar.Django.Terminals.Django_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Django_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public Django_Expression() : base(_operators)
		{
		}

		public Django_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Django.Terminals.Django_Number number;
		public Django_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Django.Terminals.Django_Literal literal;
		public Django_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Django.Expressions.Django_ExpressionRange expressionRange;
		public Django_ExpressionRange expressionRange;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Django.Expressions.Django_NotExpression notExpression;
		public Django_NotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Django.Expressions.Django_DefinedExpression definedExpression;
		public Django_DefinedExpression definedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Django.Expressions.Django_BracketsExpression bracketsExpression;
		public Django_BracketsExpression bracketsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Django.Expressions.Django_ParensExpression parensExpression;
		public Django_ParensExpression parensExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Django.Expressions.Django_FunctionExpression functionExpression;
		public Django_FunctionExpression functionExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Django.Expressions.Django_VariableExpression variableExpression;
		public Django_VariableExpression variableExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Django.Expressions.Django_SubscriptExpression subscriptExpression;
		public Django_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Django.Expressions.Django_BarExpression barExpression;
		public Django_BarExpression barExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Django.Expressions.Django_EqualityExpression equalityExpression;
		public Django_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Django.Expressions.Django_FormatExpression formatExpression;
		public Django_FormatExpression formatExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Django.Expressions.Django_InExpression inExpression;
		public Django_InExpression inExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Django.Expressions.Django_AndExpression andExpression;
		public Django_AndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Django.Expressions.Django_OrExpression orExpression;
		public Django_OrExpression orExpression;
	}

}
