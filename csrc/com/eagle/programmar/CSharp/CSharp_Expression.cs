// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.CSharp
{
	using CSharp_AdditiveExpression = com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression;
	using CSharp_AssignmentExpression = com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression;
	using CSharp_BitwiseExpression = com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseExpression;
	using CSharp_BitwiseNotExpression = com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseNotExpression;
	using CSharp_BuiltIn = com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn;
	using CSharp_CastAsExpression = com.eagle.programmar.CSharp.Expressions.CSharp_CastAsExpression;
	using CSharp_CastExpression = com.eagle.programmar.CSharp.Expressions.CSharp_CastExpression;
	using CSharp_ClassCreationExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression;
	using CSharp_ClassCreationWithInitializers = com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithInitializers;
	using CSharp_ClassCreationWithSubscript = com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithSubscript;
	using CSharp_CommentExpression = com.eagle.programmar.CSharp.Expressions.CSharp_CommentExpression;
	using CSharp_ConsoleOut = com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleOut;
	using CSharp_ConsoleRead = com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleRead;
	using CSharp_DefaultGeneric = com.eagle.programmar.CSharp.Expressions.CSharp_DefaultGeneric;
	using CSharp_Delegation = com.eagle.programmar.CSharp.Expressions.CSharp_Delegation;
	using CSharp_DotClass = com.eagle.programmar.CSharp.Expressions.CSharp_DotClass;
	using CSharp_EqualityExpression = com.eagle.programmar.CSharp.Expressions.CSharp_EqualityExpression;
	using CSharp_ExpressionList = com.eagle.programmar.CSharp.Expressions.CSharp_ExpressionList;
	using CSharp_InstanceOfExpression = com.eagle.programmar.CSharp.Expressions.CSharp_InstanceOfExpression;
	using CSharp_InterfaceCreationWithMethod = com.eagle.programmar.CSharp.Expressions.CSharp_InterfaceCreationWithMethod;
	using CSharp_LambdaBlock = com.eagle.programmar.CSharp.Expressions.CSharp_LambdaBlock;
	using CSharp_LambdaExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LambdaExpression;
	using CSharp_LambdaFunction = com.eagle.programmar.CSharp.Expressions.CSharp_LambdaFunction;
	using CSharp_LambdaParameters = com.eagle.programmar.CSharp.Expressions.CSharp_LambdaParameters;
	using CSharp_LogicalAndExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression;
	using CSharp_LogicalNotExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
	using CSharp_LogicalOrExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression;
	using CSharp_MethodInvocation = com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation;
	using CSharp_MultiplicativeExpression = com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression;
	using CSharp_NamespaceExpression = com.eagle.programmar.CSharp.Expressions.CSharp_NamespaceExpression;
	using CSharp_NegativeExpression = com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression;
	using CSharp_ParenthesizedExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression;
	using CSharp_PostIncrementExpression = com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression;
	using CSharp_PreIncrementExpression = com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression;
	using CSharp_RelationalExpression = com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression;
	using CSharp_ShiftExpression = com.eagle.programmar.CSharp.Expressions.CSharp_ShiftExpression;
	using CSharp_SubfieldExpression = com.eagle.programmar.CSharp.Expressions.CSharp_SubfieldExpression;
	using CSharp_SubscriptExpression = com.eagle.programmar.CSharp.Expressions.CSharp_SubscriptExpression;
	using CSharp_TrueFalseExpression = com.eagle.programmar.CSharp.Expressions.CSharp_TrueFalseExpression;
	using CSharp_TypeExpression = com.eagle.programmar.CSharp.Expressions.CSharp_TypeExpression;
	using CSharp_VariableExpression = com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression;
	using CSharp_MathFunction = com.eagle.programmar.CSharp.Functions.CSharp_MathFunction;
	using CSharp_PrintFunction = com.eagle.programmar.CSharp.Functions.CSharp_PrintFunction;
	using CSharp_SizeOfFunction = com.eagle.programmar.CSharp.Functions.CSharp_SizeOfFunction;
	using CSharp_StringFunction = com.eagle.programmar.CSharp.Functions.CSharp_StringFunction;
	using CSharp_IndexOfMethod = com.eagle.programmar.CSharp.Methods.CSharp_IndexOfMethod;
	using CSharp_LengthMethod = com.eagle.programmar.CSharp.Methods.CSharp_LengthMethod;
	using CSharp_StartsWithMethod = com.eagle.programmar.CSharp.Methods.CSharp_StartsWithMethod;
	using CSharp_SubstringMethod = com.eagle.programmar.CSharp.Methods.CSharp_SubstringMethod;
	using CSharp_ToStringMethod = com.eagle.programmar.CSharp.Methods.CSharp_ToStringMethod;
	using CSharp_ToUpperMethod = com.eagle.programmar.CSharp.Methods.CSharp_ToUpperMethod;
	using CSharp_TrimMethod = com.eagle.programmar.CSharp.Methods.CSharp_TrimMethod;
	using CSharp_Character_Literal = com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal;
	using CSharp_HexNumber = com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber;
	using CSharp_Literal = com.eagle.programmar.CSharp.Terminals.CSharp_Literal;
	using CSharp_Number = com.eagle.programmar.CSharp.Terminals.CSharp_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class CSharp_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public CSharp_Expression() : base(_operators)
		{
		}

		public CSharp_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.CSharp.Terminals.CSharp_HexNumber hex;
		public CSharp_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.CSharp.Terminals.CSharp_Number number;
		public CSharp_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.CSharp.Terminals.CSharp_Literal literal;
		public CSharp_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.CSharp.Terminals.CSharp_Character_Literal characters;
		public CSharp_Character_Literal characters;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.CSharp.Functions.CSharp_MathFunction mathFunction;
		public CSharp_MathFunction mathFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.CSharp.Functions.CSharp_StringFunction stringFunction;
		public CSharp_StringFunction stringFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.CSharp.Functions.CSharp_PrintFunction printFunction;
		public CSharp_PrintFunction printFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.CSharp.Expressions.CSharp_DotClass dotClass;
		public CSharp_DotClass dotClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.CSharp.Expressions.CSharp_CastExpression castExpression;
		public CSharp_CastExpression castExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.CSharp.Expressions.CSharp_ExpressionList expressionList;
		public CSharp_ExpressionList expressionList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.CSharp.Expressions.CSharp_InterfaceCreationWithMethod interfaceCreationWithMethod;
		public CSharp_InterfaceCreationWithMethod interfaceCreationWithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationExpression classCreationExpression;
		public CSharp_ClassCreationExpression classCreationExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithInitializers classCreationWithInitializers;
		public CSharp_ClassCreationWithInitializers classCreationWithInitializers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.CSharp.Expressions.CSharp_ClassCreationWithSubscript classCreationWithSubscript;
		public CSharp_ClassCreationWithSubscript classCreationWithSubscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.CSharp.Expressions.CSharp_MethodInvocation methodInvocation;
		public CSharp_MethodInvocation methodInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.CSharp.Expressions.CSharp_PreIncrementExpression preIncrementExpression;
		public CSharp_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.CSharp.Expressions.CSharp_PostIncrementExpression postIncrementExpression;
		public CSharp_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.CSharp.Expressions.CSharp_NegativeExpression negativeExpression;
		public CSharp_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseNotExpression logicalNotExpression;
		public CSharp_BitwiseNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression notExpression;
		public CSharp_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.CSharp.Expressions.CSharp_DefaultGeneric defaultGeneric;
		public CSharp_DefaultGeneric defaultGeneric;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.CSharp.Expressions.CSharp_BuiltIn builtIn;
		public CSharp_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleOut consoleOut;
		public CSharp_ConsoleOut consoleOut;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.CSharp.Expressions.CSharp_ConsoleRead consoleRead;
		public CSharp_ConsoleRead consoleRead;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.CSharp.Expressions.CSharp_VariableExpression variableExpression;
		public CSharp_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(310) com.eagle.programmar.CSharp.Expressions.CSharp_TypeExpression typeExpression;
		public CSharp_TypeExpression typeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(320) com.eagle.programmar.CSharp.Expressions.CSharp_ParenthesizedExpression parenthesizedExpression;
		public CSharp_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(330) com.eagle.programmar.CSharp.Expressions.CSharp_CommentExpression commentExpression;
		public CSharp_CommentExpression commentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(340) com.eagle.programmar.CSharp.Functions.CSharp_SizeOfFunction typeOf;
		public CSharp_SizeOfFunction typeOf;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(350) com.eagle.programmar.CSharp.Expressions.CSharp_Delegation delegation;
		public CSharp_Delegation delegation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(360) com.eagle.programmar.CSharp.Expressions.CSharp_LambdaBlock lambdaBlock;
		public CSharp_LambdaBlock lambdaBlock;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(370) com.eagle.programmar.CSharp.Expressions.CSharp_LambdaParameters lambdaParameters;
		public CSharp_LambdaParameters lambdaParameters;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.CSharp.Expressions.CSharp_SubscriptExpression subscriptExpression;
		public CSharp_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.CSharp.Expressions.CSharp_NamespaceExpression namespaceExpression;
		public CSharp_NamespaceExpression namespaceExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.CSharp.Methods.CSharp_ToUpperMethod toUpperMethod;
		public CSharp_ToUpperMethod toUpperMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.CSharp.Methods.CSharp_TrimMethod trimMethod;
		public CSharp_TrimMethod trimMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.CSharp.Methods.CSharp_ToStringMethod toStringMethod;
		public CSharp_ToStringMethod toStringMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.CSharp.Methods.CSharp_SubstringMethod substringMethod;
		public CSharp_SubstringMethod substringMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.CSharp.Methods.CSharp_IndexOfMethod indexOfMethod;
		public CSharp_IndexOfMethod indexOfMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.CSharp.Methods.CSharp_StartsWithMethod startswithMethod;
		public CSharp_StartsWithMethod startswithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.CSharp.Methods.CSharp_LengthMethod lengthMethod;
		public CSharp_LengthMethod lengthMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.CSharp.Expressions.CSharp_SubfieldExpression subfieldExpression;
		public CSharp_SubfieldExpression subfieldExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.CSharp.Expressions.CSharp_MultiplicativeExpression multiplicativeExpression;
		public CSharp_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.CSharp.Expressions.CSharp_AdditiveExpression additiveExpression;
		public CSharp_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.CSharp.Expressions.CSharp_ShiftExpression shiftExpression;
		public CSharp_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.CSharp.Expressions.CSharp_RelationalExpression relationalExpression;
		public CSharp_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.CSharp.Expressions.CSharp_InstanceOfExpression instanceOfExpression;
		public CSharp_InstanceOfExpression instanceOfExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1150) com.eagle.programmar.CSharp.Expressions.CSharp_CastAsExpression castAsExpression;
		public CSharp_CastAsExpression castAsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1160) com.eagle.programmar.CSharp.Expressions.CSharp_EqualityExpression equalityExpression;
		public CSharp_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1170) com.eagle.programmar.CSharp.Expressions.CSharp_BitwiseExpression andExpression;
		public CSharp_BitwiseExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1180) com.eagle.programmar.CSharp.Expressions.CSharp_LogicalAndExpression conditionalAndExpression;
		public CSharp_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1190) com.eagle.programmar.CSharp.Expressions.CSharp_LogicalOrExpression conditionalOrExpression;
		public CSharp_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1200) com.eagle.programmar.CSharp.Expressions.CSharp_TrueFalseExpression trueFalseExpression;
		public CSharp_TrueFalseExpression trueFalseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1210) com.eagle.programmar.CSharp.Expressions.CSharp_AssignmentExpression assignmentExpression;
		public CSharp_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1220) com.eagle.programmar.CSharp.Expressions.CSharp_LambdaFunction lambdaFunction;
		public CSharp_LambdaFunction lambdaFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1230) com.eagle.programmar.CSharp.Expressions.CSharp_LambdaExpression lambdaExpression;
		public CSharp_LambdaExpression lambdaExpression;
	}

}
