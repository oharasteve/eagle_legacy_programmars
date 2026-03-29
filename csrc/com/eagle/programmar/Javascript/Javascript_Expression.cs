// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

namespace com.eagle.programmar.Javascript
{
	using Javascript_AdditiveExpression = com.eagle.programmar.Javascript.Expressions.Javascript_AdditiveExpression;
	using Javascript_AssignmentExpression = com.eagle.programmar.Javascript.Expressions.Javascript_AssignmentExpression;
	using Javascript_BitwiseAndExpression = com.eagle.programmar.Javascript.Expressions.Javascript_BitwiseAndExpression;
	using Javascript_BitwiseNotExpression = com.eagle.programmar.Javascript.Expressions.Javascript_BitwiseNotExpression;
	using Javascript_BitwiseOrExpression = com.eagle.programmar.Javascript.Expressions.Javascript_BitwiseOrExpression;
	using Javascript_BuiltInVar = com.eagle.programmar.Javascript.Expressions.Javascript_BuiltInVar;
	using Javascript_CastExpression = com.eagle.programmar.Javascript.Expressions.Javascript_CastExpression;
	using Javascript_ClassCreationExpression = com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationExpression;
	using Javascript_ClassCreationWithInitializers = com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationWithInitializers;
	using Javascript_ClassCreationWithSubscript = com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationWithSubscript;
	using Javascript_ClassExpr = com.eagle.programmar.Javascript.Expressions.Javascript_ClassExpr;
	using Javascript_CommentExpression = com.eagle.programmar.Javascript.Expressions.Javascript_CommentExpression;
	using Javascript_DeleteExpression = com.eagle.programmar.Javascript.Expressions.Javascript_DeleteExpression;
	using Javascript_DotClass = com.eagle.programmar.Javascript.Expressions.Javascript_DotClass;
	using Javascript_EllipsisExpr = com.eagle.programmar.Javascript.Expressions.Javascript_EllipsisExpr;
	using Javascript_EqualityExpression = com.eagle.programmar.Javascript.Expressions.Javascript_EqualityExpression;
	using Javascript_ExpressionList = com.eagle.programmar.Javascript.Expressions.Javascript_ExpressionList;
	using Javascript_FunctionCall = com.eagle.programmar.Javascript.Expressions.Javascript_FunctionCall;
	using Javascript_InExpression = com.eagle.programmar.Javascript.Expressions.Javascript_InExpression;
	using Javascript_InstanceOfExpression = com.eagle.programmar.Javascript.Expressions.Javascript_InstanceOfExpression;
	using Javascript_LogicalAndExpression = com.eagle.programmar.Javascript.Expressions.Javascript_LogicalAndExpression;
	using Javascript_LogicalNotExpression = com.eagle.programmar.Javascript.Expressions.Javascript_LogicalNotExpression;
	using Javascript_LogicalOrExpression = com.eagle.programmar.Javascript.Expressions.Javascript_LogicalOrExpression;
	using Javascript_LogicalXorExpression = com.eagle.programmar.Javascript.Expressions.Javascript_LogicalXorExpression;
	using Javascript_MultiplicativeExpression = com.eagle.programmar.Javascript.Expressions.Javascript_MultiplicativeExpression;
	using Javascript_NegativeExpression = com.eagle.programmar.Javascript.Expressions.Javascript_NegativeExpression;
	using Javascript_NewNoArgsExpression = com.eagle.programmar.Javascript.Expressions.Javascript_NewNoArgsExpression;
	using Javascript_ObjectLiteral = com.eagle.programmar.Javascript.Expressions.Javascript_ObjectLiteral;
	using Javascript_Parenthesized_Expression = com.eagle.programmar.Javascript.Expressions.Javascript_Parenthesized_Expression;
	using Javascript_PostIncrementExpression = com.eagle.programmar.Javascript.Expressions.Javascript_PostIncrementExpression;
	using Javascript_Power_Expression = com.eagle.programmar.Javascript.Expressions.Javascript_Power_Expression;
	using Javascript_PreIncrementExpression = com.eagle.programmar.Javascript.Expressions.Javascript_PreIncrementExpression;
	using Javascript_RelationalExpression = com.eagle.programmar.Javascript.Expressions.Javascript_RelationalExpression;
	using Javascript_ShiftExpression = com.eagle.programmar.Javascript.Expressions.Javascript_ShiftExpression;
	using Javascript_SimpleArray = com.eagle.programmar.Javascript.Expressions.Javascript_SimpleArray;
	using Javascript_Subfield = com.eagle.programmar.Javascript.Expressions.Javascript_Subfield;
	using Javascript_SubscriptExpression = com.eagle.programmar.Javascript.Expressions.Javascript_SubscriptExpression;
	using Javascript_TemplateExpr = com.eagle.programmar.Javascript.Expressions.Javascript_TemplateExpr;
	using Javascript_TrueFalseExpression = com.eagle.programmar.Javascript.Expressions.Javascript_TrueFalseExpression;
	using Javascript_TypeOfExpr = com.eagle.programmar.Javascript.Expressions.Javascript_TypeOfExpr;
	using Javascript_VariableExpression = com.eagle.programmar.Javascript.Expressions.Javascript_VariableExpression;
	using Javascript_VoidExpr = com.eagle.programmar.Javascript.Expressions.Javascript_VoidExpr;
	using Javascript_AwaitFunctionCall = com.eagle.programmar.Javascript.Functions.Javascript_AwaitFunctionCall;
	using Javascript_EvalFunction = com.eagle.programmar.Javascript.Functions.Javascript_EvalFunction;
	using Javascript_FunctionExpr = com.eagle.programmar.Javascript.Functions.Javascript_FunctionExpr;
	using Javascript_LambdaFunction = com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction;
	using Javascript_Length = com.eagle.programmar.Javascript.Functions.Javascript_Length;
	using Javascript_MathFloor = com.eagle.programmar.Javascript.Functions.Javascript_MathFloor;
	using Javascript_ParenthesizedFunction = com.eagle.programmar.Javascript.Functions.Javascript_ParenthesizedFunction;
	using Javascript_StartsWith = com.eagle.programmar.Javascript.Functions.Javascript_StartsWith;
	using Javascript_StrangeFunction = com.eagle.programmar.Javascript.Functions.Javascript_StrangeFunction;
	using Javascript_StringFunction = com.eagle.programmar.Javascript.Functions.Javascript_StringFunction;
	using Javascript_HexNumber = com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber;
	using Javascript_Literal = com.eagle.programmar.Javascript.Terminals.Javascript_Literal;
	using Javascript_Number = com.eagle.programmar.Javascript.Terminals.Javascript_Number;
	using Javascript_RegularExpression = com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Javascript_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public Javascript_Expression() : base(_operators)
		{
		}

		public Javascript_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Javascript.Terminals.Javascript_HexNumber hex;
		public Javascript_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Javascript.Terminals.Javascript_Number number;
		public Javascript_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Javascript.Terminals.Javascript_Literal literal;
		public Javascript_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.Javascript.Terminals.Javascript_RegularExpression regEx;
		public Javascript_RegularExpression regEx;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Javascript.Functions.Javascript_Length lengthFunction;
		public Javascript_Length lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Javascript.Functions.Javascript_MathFloor mathFloor;
		public Javascript_MathFloor mathFloor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Javascript.Functions.Javascript_StartsWith startsWith;
		public Javascript_StartsWith startsWith;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Javascript.Functions.Javascript_EvalFunction evalFunction;
		public Javascript_EvalFunction evalFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Javascript.Expressions.Javascript_DotClass dotClass;
		public Javascript_DotClass dotClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Javascript.Expressions.Javascript_CastExpression castExpression;
		public Javascript_CastExpression castExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Javascript.Expressions.Javascript_ExpressionList expressionList;
		public Javascript_ExpressionList expressionList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Javascript.Functions.Javascript_StringFunction stringFunction;
		public Javascript_StringFunction stringFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Javascript.Expressions.Javascript_DeleteExpression deleteExpression;
		public Javascript_DeleteExpression deleteExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationExpression classCreationExpression;
		public Javascript_ClassCreationExpression classCreationExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationWithInitializers classCreationWithInitializers;
		public Javascript_ClassCreationWithInitializers classCreationWithInitializers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Javascript.Expressions.Javascript_ClassCreationWithSubscript classCreationWithSubscript;
		public Javascript_ClassCreationWithSubscript classCreationWithSubscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Javascript.Expressions.Javascript_NewNoArgsExpression newNoArgsExpression;
		public Javascript_NewNoArgsExpression newNoArgsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Javascript.Functions.Javascript_AwaitFunctionCall awaitFunctionCall;
		public Javascript_AwaitFunctionCall awaitFunctionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.Javascript.Expressions.Javascript_FunctionCall functionCall;
		public Javascript_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.Javascript.Expressions.Javascript_PreIncrementExpression preIncrementExpression;
		public Javascript_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.Javascript.Expressions.Javascript_PostIncrementExpression postIncrementExpression;
		public Javascript_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.Javascript.Expressions.Javascript_NegativeExpression negativeExpression;
		public Javascript_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.Javascript.Expressions.Javascript_BitwiseNotExpression logicalNotExpression;
		public Javascript_BitwiseNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.Javascript.Expressions.Javascript_LogicalNotExpression notExpression;
		public Javascript_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.Javascript.Expressions.Javascript_BuiltInVar builtInVar;
		public Javascript_BuiltInVar builtInVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(310) com.eagle.programmar.Javascript.Expressions.Javascript_TemplateExpr templateExpr;
		public Javascript_TemplateExpr templateExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(320) com.eagle.programmar.Javascript.Functions.Javascript_LambdaFunction lambdaFunction;
		public Javascript_LambdaFunction lambdaFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(330) com.eagle.programmar.Javascript.Expressions.Javascript_VariableExpression variableExpression;
		public Javascript_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(340) com.eagle.programmar.Javascript.Functions.Javascript_StrangeFunction strangeFunction;
		public Javascript_StrangeFunction strangeFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(350) com.eagle.programmar.Javascript.Functions.Javascript_ParenthesizedFunction parenthesizedFunction;
		public Javascript_ParenthesizedFunction parenthesizedFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(360) com.eagle.programmar.Javascript.Expressions.Javascript_Parenthesized_Expression parenthesized_Expression;
		public Javascript_Parenthesized_Expression parenthesized_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(370) com.eagle.programmar.Javascript.Expressions.Javascript_SimpleArray simpleArray;
		public Javascript_SimpleArray simpleArray;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(380) com.eagle.programmar.Javascript.Expressions.Javascript_CommentExpression commentExpression;
		public Javascript_CommentExpression commentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(390) com.eagle.programmar.Javascript.Expressions.Javascript_ObjectLiteral objectLiteral;
		public Javascript_ObjectLiteral objectLiteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(400) com.eagle.programmar.Javascript.Functions.Javascript_FunctionExpr functionExpr;
		public Javascript_FunctionExpr functionExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(410) com.eagle.programmar.Javascript.Expressions.Javascript_ClassExpr classExpr;
		public Javascript_ClassExpr classExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(420) com.eagle.programmar.Javascript.Expressions.Javascript_TypeOfExpr typeOfExpr;
		public Javascript_TypeOfExpr typeOfExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(430) com.eagle.programmar.Javascript.Expressions.Javascript_VoidExpr voidExpr;
		public Javascript_VoidExpr voidExpr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(440) com.eagle.programmar.Javascript.Expressions.Javascript_EllipsisExpr ellipsisExpr;
		public Javascript_EllipsisExpr ellipsisExpr;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Javascript.Expressions.Javascript_SubscriptExpression subscriptExpression;
		public Javascript_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Javascript.Expressions.Javascript_Subfield subfield;
		public Javascript_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Javascript.Expressions.Javascript_Power_Expression power_Expression;
		public Javascript_Power_Expression power_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Javascript.Expressions.Javascript_MultiplicativeExpression multiplicativeExpression;
		public Javascript_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Javascript.Expressions.Javascript_AdditiveExpression additiveExpression;
		public Javascript_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Javascript.Expressions.Javascript_ShiftExpression shiftExpression;
		public Javascript_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Javascript.Expressions.Javascript_RelationalExpression relationalExpression;
		public Javascript_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Javascript.Expressions.Javascript_InstanceOfExpression instanceOfExpression;
		public Javascript_InstanceOfExpression instanceOfExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Javascript.Expressions.Javascript_InExpression inExpression;
		public Javascript_InExpression inExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Javascript.Expressions.Javascript_EqualityExpression equalityExpression;
		public Javascript_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Javascript.Expressions.Javascript_BitwiseAndExpression andExpression;
		public Javascript_BitwiseAndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Javascript.Expressions.Javascript_LogicalXorExpression exclusiveOrExpression;
		public Javascript_LogicalXorExpression exclusiveOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Javascript.Expressions.Javascript_BitwiseOrExpression inclusiveOrExpression;
		public Javascript_BitwiseOrExpression inclusiveOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.Javascript.Expressions.Javascript_LogicalAndExpression conditionalAndExpression;
		public Javascript_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.Javascript.Expressions.Javascript_LogicalOrExpression conditionalOrExpression;
		public Javascript_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1150) com.eagle.programmar.Javascript.Expressions.Javascript_TrueFalseExpression trueFalseExpression;
		public Javascript_TrueFalseExpression trueFalseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1160) com.eagle.programmar.Javascript.Expressions.Javascript_AssignmentExpression assignmentExpression;
		public Javascript_AssignmentExpression assignmentExpression;
	}

}
