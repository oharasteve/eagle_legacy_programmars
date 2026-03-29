// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Ruby
{
	using Ruby_AdditiveExpression = com.eagle.programmar.Ruby.Expressions.Ruby_AdditiveExpression;
	using Ruby_AssignmentExpression = com.eagle.programmar.Ruby.Expressions.Ruby_AssignmentExpression;
	using Ruby_BitwiseNotExpression = com.eagle.programmar.Ruby.Expressions.Ruby_BitwiseNotExpression;
	using Ruby_BracketsExpression = com.eagle.programmar.Ruby.Expressions.Ruby_BracketsExpression;
	using Ruby_BuiltIn = com.eagle.programmar.Ruby.Expressions.Ruby_BuiltIn;
	using Ruby_EqualityExpression = com.eagle.programmar.Ruby.Expressions.Ruby_EqualityExpression;
	using Ruby_FunctionCall = com.eagle.programmar.Ruby.Expressions.Ruby_FunctionCall;
	using Ruby_LogicalAndExpression = com.eagle.programmar.Ruby.Expressions.Ruby_LogicalAndExpression;
	using Ruby_LogicalNotExpression = com.eagle.programmar.Ruby.Expressions.Ruby_LogicalNotExpression;
	using Ruby_LogicalOrExpression = com.eagle.programmar.Ruby.Expressions.Ruby_LogicalOrExpression;
	using Ruby_MultiplicativeExpression = com.eagle.programmar.Ruby.Expressions.Ruby_MultiplicativeExpression;
	using Ruby_NegativeExpression = com.eagle.programmar.Ruby.Expressions.Ruby_NegativeExpression;
	using Ruby_ParenthesizedExpression = com.eagle.programmar.Ruby.Expressions.Ruby_ParenthesizedExpression;
	using Ruby_PostIncrementExpression = com.eagle.programmar.Ruby.Expressions.Ruby_PostIncrementExpression;
	using Ruby_PreIncrementExpression = com.eagle.programmar.Ruby.Expressions.Ruby_PreIncrementExpression;
	using Ruby_RangeExpression = com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression;
	using Ruby_RelationalExpression = com.eagle.programmar.Ruby.Expressions.Ruby_RelationalExpression;
	using Ruby_Subfield = com.eagle.programmar.Ruby.Expressions.Ruby_Subfield;
	using Ruby_SubscriptExpression = com.eagle.programmar.Ruby.Expressions.Ruby_SubscriptExpression;
	using Ruby_VariableExpression = com.eagle.programmar.Ruby.Expressions.Ruby_VariableExpression;
	using Ruby_DownToMethod = com.eagle.programmar.Ruby.Functions.Ruby_DownToMethod;
	using Ruby_LengthMethod = com.eagle.programmar.Ruby.Functions.Ruby_LengthMethod;
	using Ruby_StartWithMethod = com.eagle.programmar.Ruby.Functions.Ruby_StartWithMethod;
	using Ruby_Literal = com.eagle.programmar.Ruby.Terminals.Ruby_Literal;
	using Ruby_Number = com.eagle.programmar.Ruby.Terminals.Ruby_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Ruby_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Ruby_Expression() : base(_operators)
		{
		}

		public Ruby_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Ruby.Terminals.Ruby_Number number;
		public Ruby_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Ruby.Terminals.Ruby_Literal literal;
		public Ruby_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Ruby.Expressions.Ruby_FunctionCall functionCall;
		public Ruby_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Ruby.Expressions.Ruby_PreIncrementExpression preIncrementExpression;
		public Ruby_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Ruby.Expressions.Ruby_PostIncrementExpression postIncrementExpression;
		public Ruby_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Ruby.Expressions.Ruby_NegativeExpression negativeExpression;
		public Ruby_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Ruby.Expressions.Ruby_BitwiseNotExpression logicalNotExpression;
		public Ruby_BitwiseNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Ruby.Expressions.Ruby_LogicalNotExpression notExpression;
		public Ruby_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Ruby.Expressions.Ruby_BuiltIn builtIn;
		public Ruby_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Ruby.Expressions.Ruby_VariableExpression variableExpression;
		public Ruby_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Ruby.Expressions.Ruby_BracketsExpression bracketsExpression;
		public Ruby_BracketsExpression bracketsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Ruby.Expressions.Ruby_ParenthesizedExpression parenthesizedExpression;
		public Ruby_ParenthesizedExpression parenthesizedExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Ruby.Expressions.Ruby_SubscriptExpression subscriptExpression;
		public Ruby_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Ruby.Functions.Ruby_StartWithMethod startwithMethod;
		public Ruby_StartWithMethod startwithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Ruby.Functions.Ruby_LengthMethod lengthMethod;
		public Ruby_LengthMethod lengthMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Ruby.Functions.Ruby_DownToMethod downTo;
		public Ruby_DownToMethod downTo;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Ruby.Expressions.Ruby_Subfield subfield;
		public Ruby_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Ruby.Expressions.Ruby_MultiplicativeExpression multiplicativeExpression;
		public Ruby_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Ruby.Expressions.Ruby_AdditiveExpression additiveExpression;
		public Ruby_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Ruby.Expressions.Ruby_RelationalExpression relationalExpression;
		public Ruby_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Ruby.Expressions.Ruby_EqualityExpression equalityExpression;
		public Ruby_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Ruby.Expressions.Ruby_LogicalAndExpression conditionalAndExpression;
		public Ruby_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Ruby.Expressions.Ruby_LogicalOrExpression conditionalOrExpression;
		public Ruby_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Ruby.Expressions.Ruby_AssignmentExpression assignmentExpression;
		public Ruby_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Ruby.Expressions.Ruby_RangeExpression rangeExpression;
		public Ruby_RangeExpression rangeExpression;
	}

}
