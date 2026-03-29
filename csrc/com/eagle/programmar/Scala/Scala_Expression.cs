// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Scala
{
	using Scala_AdditiveExpression = com.eagle.programmar.Scala.Expressions.Scala_AdditiveExpression;
	using Scala_AssignmentExpression = com.eagle.programmar.Scala.Expressions.Scala_AssignmentExpression;
	using Scala_BracesExpression = com.eagle.programmar.Scala.Expressions.Scala_BracesExpression;
	using Scala_BuiltIn = com.eagle.programmar.Scala.Expressions.Scala_BuiltIn;
	using Scala_EqualityExpression = com.eagle.programmar.Scala.Expressions.Scala_EqualityExpression;
	using Scala_FunctionCall = com.eagle.programmar.Scala.Expressions.Scala_FunctionCall;
	using Scala_LogicalAndExpression = com.eagle.programmar.Scala.Expressions.Scala_LogicalAndExpression;
	using Scala_LogicalNotExpression = com.eagle.programmar.Scala.Expressions.Scala_LogicalNotExpression;
	using Scala_LogicalOrExpression = com.eagle.programmar.Scala.Expressions.Scala_LogicalOrExpression;
	using Scala_MultiplicativeExpression = com.eagle.programmar.Scala.Expressions.Scala_MultiplicativeExpression;
	using Scala_NegativeExpression = com.eagle.programmar.Scala.Expressions.Scala_NegativeExpression;
	using Scala_NotExpression = com.eagle.programmar.Scala.Expressions.Scala_NotExpression;
	using Scala_ParenthesizedExpression = com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression;
	using Scala_PostIncrementExpression = com.eagle.programmar.Scala.Expressions.Scala_PostIncrementExpression;
	using Scala_PreIncrementExpression = com.eagle.programmar.Scala.Expressions.Scala_PreIncrementExpression;
	using Scala_RangeExpression = com.eagle.programmar.Scala.Expressions.Scala_RangeExpression;
	using Scala_RelationalExpression = com.eagle.programmar.Scala.Expressions.Scala_RelationalExpression;
	using Scala_VariableExpression = com.eagle.programmar.Scala.Expressions.Scala_VariableExpression;
	using Scala_EqualsMethod = com.eagle.programmar.Scala.Functions.Scala_EqualsMethod;
	using Scala_LengthMethod = com.eagle.programmar.Scala.Functions.Scala_LengthMethod;
	using Scala_ListFunction = com.eagle.programmar.Scala.Functions.Scala_ListFunction;
	using Scala_ReverseMethod = com.eagle.programmar.Scala.Functions.Scala_ReverseMethod;
	using Scala_StartsWithMethod = com.eagle.programmar.Scala.Functions.Scala_StartsWithMethod;
	using Scala_Literal = com.eagle.programmar.Scala.Terminals.Scala_Literal;
	using Scala_Number = com.eagle.programmar.Scala.Terminals.Scala_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Scala_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Scala_Expression() : base(_operators)
		{
		}

		public Scala_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Scala.Terminals.Scala_Number number;
		public Scala_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Scala.Terminals.Scala_Literal literal;
		public Scala_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Scala.Expressions.Scala_FunctionCall functionCall;
		public Scala_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Scala.Expressions.Scala_PreIncrementExpression preIncrementExpression;
		public Scala_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Scala.Expressions.Scala_PostIncrementExpression postIncrementExpression;
		public Scala_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Scala.Expressions.Scala_NegativeExpression negativeExpression;
		public Scala_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Scala.Expressions.Scala_LogicalNotExpression logicalNotExpression;
		public Scala_LogicalNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Scala.Expressions.Scala_NotExpression notExpression;
		public Scala_NotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Scala.Expressions.Scala_BuiltIn builtIn;
		public Scala_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Scala.Functions.Scala_ListFunction listFunction;
		public Scala_ListFunction listFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Scala.Expressions.Scala_VariableExpression variableExpression;
		public Scala_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Scala.Expressions.Scala_BracesExpression bracesExpression;
		public Scala_BracesExpression bracesExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Scala.Functions.Scala_ReverseMethod rangeREversed;
		public Scala_ReverseMethod rangeREversed;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression parenthesizedExpression;
		public Scala_ParenthesizedExpression parenthesizedExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Scala.Functions.Scala_EqualsMethod equalsMethod;
		public Scala_EqualsMethod equalsMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Scala.Functions.Scala_LengthMethod lengthMethod;
		public Scala_LengthMethod lengthMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Scala.Functions.Scala_StartsWithMethod startswithMethod;
		public Scala_StartsWithMethod startswithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Scala.Expressions.Scala_MultiplicativeExpression multiplicativeExpression;
		public Scala_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Scala.Expressions.Scala_AdditiveExpression additiveExpression;
		public Scala_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Scala.Expressions.Scala_RelationalExpression relationalExpression;
		public Scala_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Scala.Expressions.Scala_EqualityExpression equalityExpression;
		public Scala_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Scala.Expressions.Scala_LogicalAndExpression conditionalAndExpression;
		public Scala_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Scala.Expressions.Scala_LogicalOrExpression conditionalOrExpression;
		public Scala_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Scala.Expressions.Scala_AssignmentExpression assignmentExpression;
		public Scala_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Scala.Expressions.Scala_RangeExpression rangeExpression;
		public Scala_RangeExpression rangeExpression;
	}

}
