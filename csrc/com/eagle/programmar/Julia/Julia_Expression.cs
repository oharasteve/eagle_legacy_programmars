// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

namespace com.eagle.programmar.Julia
{
	using Julia_AdditiveExpression = com.eagle.programmar.Julia.Expressions.Julia_AdditiveExpression;
	using Julia_AssignmentExpression = com.eagle.programmar.Julia.Expressions.Julia_AssignmentExpression;
	using Julia_BracketsExpression = com.eagle.programmar.Julia.Expressions.Julia_BracketsExpression;
	using Julia_BuiltIn = com.eagle.programmar.Julia.Expressions.Julia_BuiltIn;
	using Julia_EqualityExpression = com.eagle.programmar.Julia.Expressions.Julia_EqualityExpression;
	using Julia_FunctionCall = com.eagle.programmar.Julia.Expressions.Julia_FunctionCall;
	using Julia_LogicalAndExpression = com.eagle.programmar.Julia.Expressions.Julia_LogicalAndExpression;
	using Julia_LogicalNotExpression = com.eagle.programmar.Julia.Expressions.Julia_LogicalNotExpression;
	using Julia_LogicalOrExpression = com.eagle.programmar.Julia.Expressions.Julia_LogicalOrExpression;
	using Julia_MultiplicativeExpression = com.eagle.programmar.Julia.Expressions.Julia_MultiplicativeExpression;
	using Julia_NegativeExpression = com.eagle.programmar.Julia.Expressions.Julia_NegativeExpression;
	using Julia_ParenthesizedExpression = com.eagle.programmar.Julia.Expressions.Julia_ParenthesizedExpression;
	using Julia_PostIncrementExpression = com.eagle.programmar.Julia.Expressions.Julia_PostIncrementExpression;
	using Julia_PreIncrementExpression = com.eagle.programmar.Julia.Expressions.Julia_PreIncrementExpression;
	using Julia_RangeExpression = com.eagle.programmar.Julia.Expressions.Julia_RangeExpression;
	using Julia_RelationalExpression = com.eagle.programmar.Julia.Expressions.Julia_RelationalExpression;
	using Julia_Subfield = com.eagle.programmar.Julia.Expressions.Julia_Subfield;
	using Julia_SubscriptExpression = com.eagle.programmar.Julia.Expressions.Julia_SubscriptExpression;
	using Julia_VariableExpression = com.eagle.programmar.Julia.Expressions.Julia_VariableExpression;
	using Julia_DivFunction = com.eagle.programmar.Julia.Functions.Julia_DivFunction;
	using Julia_LengthFunction = com.eagle.programmar.Julia.Functions.Julia_LengthFunction;
	using Julia_StartsWithFunction = com.eagle.programmar.Julia.Functions.Julia_StartsWithFunction;
	using Julia_StringFunction = com.eagle.programmar.Julia.Functions.Julia_StringFunction;
	using Julia_SubStringFunction = com.eagle.programmar.Julia.Functions.Julia_SubStringFunction;
	using Julia_Literal = com.eagle.programmar.Julia.Terminals.Julia_Literal;
	using Julia_Number = com.eagle.programmar.Julia.Terminals.Julia_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Julia_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Julia_Expression() : base(_operators)
		{
		}

		public Julia_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Julia.Terminals.Julia_Number number;
		public Julia_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Julia.Terminals.Julia_Literal literal;
		public Julia_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Julia.Functions.Julia_DivFunction divFunction;
		public Julia_DivFunction divFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Julia.Functions.Julia_LengthFunction lengthFunction;
		public Julia_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Julia.Functions.Julia_StartsWithFunction startswithFunction;
		public Julia_StartsWithFunction startswithFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Julia.Functions.Julia_StringFunction stringFunction;
		public Julia_StringFunction stringFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Julia.Functions.Julia_SubStringFunction subStringFunction;
		public Julia_SubStringFunction subStringFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Julia.Expressions.Julia_FunctionCall functionCall;
		public Julia_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Julia.Expressions.Julia_PreIncrementExpression preIncrementExpression;
		public Julia_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Julia.Expressions.Julia_PostIncrementExpression postIncrementExpression;
		public Julia_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Julia.Expressions.Julia_NegativeExpression negativeExpression;
		public Julia_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Julia.Expressions.Julia_LogicalNotExpression logicalNotExpression;
		public Julia_LogicalNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Julia.Expressions.Julia_BuiltIn builtIn;
		public Julia_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Julia.Expressions.Julia_VariableExpression variableExpression;
		public Julia_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Julia.Expressions.Julia_BracketsExpression bracketsExpression;
		public Julia_BracketsExpression bracketsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Julia.Expressions.Julia_ParenthesizedExpression parenthesizedExpression;
		public Julia_ParenthesizedExpression parenthesizedExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Julia.Expressions.Julia_SubscriptExpression subscriptExpression;
		public Julia_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Julia.Expressions.Julia_Subfield subfield;
		public Julia_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Julia.Expressions.Julia_MultiplicativeExpression multiplicativeExpression;
		public Julia_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Julia.Expressions.Julia_AdditiveExpression additiveExpression;
		public Julia_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Julia.Expressions.Julia_RelationalExpression relationalExpression;
		public Julia_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Julia.Expressions.Julia_EqualityExpression equalityExpression;
		public Julia_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Julia.Expressions.Julia_LogicalAndExpression conditionalAndExpression;
		public Julia_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Julia.Expressions.Julia_LogicalOrExpression conditionalOrExpression;
		public Julia_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Julia.Expressions.Julia_AssignmentExpression assignmentExpression;
		public Julia_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Julia.Expressions.Julia_RangeExpression rangeExpression;
		public Julia_RangeExpression rangeExpression;
	}

}
