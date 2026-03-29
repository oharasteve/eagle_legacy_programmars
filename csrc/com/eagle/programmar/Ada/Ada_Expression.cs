// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

namespace com.eagle.programmar.Ada
{
	using Ada_AdditiveExpression = com.eagle.programmar.Ada.Expressions.Ada_AdditiveExpression;
	using Ada_AssignmentExpression = com.eagle.programmar.Ada.Expressions.Ada_AssignmentExpression;
	using Ada_BracketsExpression = com.eagle.programmar.Ada.Expressions.Ada_BracketsExpression;
	using Ada_BuiltIn = com.eagle.programmar.Ada.Expressions.Ada_BuiltIn;
	using Ada_EqualityExpression = com.eagle.programmar.Ada.Expressions.Ada_EqualityExpression;
	using Ada_FunctionCall = com.eagle.programmar.Ada.Expressions.Ada_FunctionCall;
	using Ada_LogicalAndExpression = com.eagle.programmar.Ada.Expressions.Ada_LogicalAndExpression;
	using Ada_LogicalNotExpression = com.eagle.programmar.Ada.Expressions.Ada_LogicalNotExpression;
	using Ada_LogicalOrExpression = com.eagle.programmar.Ada.Expressions.Ada_LogicalOrExpression;
	using Ada_MultiplicativeExpression = com.eagle.programmar.Ada.Expressions.Ada_MultiplicativeExpression;
	using Ada_NegativeExpression = com.eagle.programmar.Ada.Expressions.Ada_NegativeExpression;
	using Ada_ParenthesizedExpression = com.eagle.programmar.Ada.Expressions.Ada_ParenthesizedExpression;
	using Ada_PostIncrementExpression = com.eagle.programmar.Ada.Expressions.Ada_PostIncrementExpression;
	using Ada_PreIncrementExpression = com.eagle.programmar.Ada.Expressions.Ada_PreIncrementExpression;
	using Ada_RangeExpression = com.eagle.programmar.Ada.Expressions.Ada_RangeExpression;
	using Ada_RelationalExpression = com.eagle.programmar.Ada.Expressions.Ada_RelationalExpression;
	using Ada_Subfield = com.eagle.programmar.Ada.Expressions.Ada_Subfield;
	using Ada_VariableExpression = com.eagle.programmar.Ada.Expressions.Ada_VariableExpression;
	using Ada_LengthFunction = com.eagle.programmar.Ada.Functions.Ada_LengthFunction;
	using Ada_SliceFunction = com.eagle.programmar.Ada.Functions.Ada_SliceFunction;
	using Ada_UnboundFunction = com.eagle.programmar.Ada.Functions.Ada_UnboundFunction;
	using Ada_Literal = com.eagle.programmar.Ada.Terminals.Ada_Literal;
	using Ada_Number = com.eagle.programmar.Ada.Terminals.Ada_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Ada_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Ada_Expression() : base(_operators)
		{
		}

		public Ada_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Ada.Terminals.Ada_Number number;
		public Ada_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Ada.Terminals.Ada_Literal literal;
		public Ada_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Ada.Functions.Ada_SliceFunction sliceFunction;
		public Ada_SliceFunction sliceFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Ada.Functions.Ada_LengthFunction lengthFunction;
		public Ada_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Ada.Functions.Ada_UnboundFunction builtinFunction;
		public Ada_UnboundFunction builtinFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Ada.Expressions.Ada_PreIncrementExpression preIncrementExpression;
		public Ada_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Ada.Expressions.Ada_PostIncrementExpression postIncrementExpression;
		public Ada_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Ada.Expressions.Ada_NegativeExpression negativeExpression;
		public Ada_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Ada.Expressions.Ada_LogicalNotExpression logicalNotExpression;
		public Ada_LogicalNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Ada.Expressions.Ada_BuiltIn builtIn;
		public Ada_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Ada.Expressions.Ada_FunctionCall functionCall;
		public Ada_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Ada.Expressions.Ada_VariableExpression variableExpression;
		public Ada_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Ada.Expressions.Ada_BracketsExpression bracketsExpression;
		public Ada_BracketsExpression bracketsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Ada.Expressions.Ada_ParenthesizedExpression parenthesizedExpression;
		public Ada_ParenthesizedExpression parenthesizedExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Ada.Expressions.Ada_Subfield subfield;
		public Ada_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Ada.Expressions.Ada_MultiplicativeExpression multiplicativeExpression;
		public Ada_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Ada.Expressions.Ada_AdditiveExpression additiveExpression;
		public Ada_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Ada.Expressions.Ada_RelationalExpression relationalExpression;
		public Ada_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Ada.Expressions.Ada_EqualityExpression equalityExpression;
		public Ada_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Ada.Expressions.Ada_LogicalAndExpression conditionalAndExpression;
		public Ada_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Ada.Expressions.Ada_LogicalOrExpression conditionalOrExpression;
		public Ada_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Ada.Expressions.Ada_AssignmentExpression assignmentExpression;
		public Ada_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Ada.Expressions.Ada_RangeExpression rangeExpression;
		public Ada_RangeExpression rangeExpression;
	}

}
