// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Java
{
	using Java_AdditiveExpression = com.eagle.programmar.Java.Expressions.Java_AdditiveExpression;
	using Java_AnnotationInvocation = com.eagle.programmar.Java.Expressions.Java_AnnotationInvocation;
	using Java_AssignmentExpression = com.eagle.programmar.Java.Expressions.Java_AssignmentExpression;
	using Java_BitwiseExpression = com.eagle.programmar.Java.Expressions.Java_BitwiseExpression;
	using Java_BitwiseNotExpression = com.eagle.programmar.Java.Expressions.Java_BitwiseNotExpression;
	using Java_BuiltIn = com.eagle.programmar.Java.Expressions.Java_BuiltIn;
	using Java_CastExpression = com.eagle.programmar.Java.Expressions.Java_CastExpression;
	using Java_ClassCreationExpression = com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression;
	using Java_ClassCreationWithInitializers = com.eagle.programmar.Java.Expressions.Java_ClassCreationWithInitializers;
	using Java_ClassCreationWithSubscript = com.eagle.programmar.Java.Expressions.Java_ClassCreationWithSubscript;
	using Java_ColonColon = com.eagle.programmar.Java.Expressions.Java_ColonColon;
	using Java_ColonColonNew = com.eagle.programmar.Java.Expressions.Java_ColonColonNew;
	using Java_ColonColonType = com.eagle.programmar.Java.Expressions.Java_ColonColonType;
	using Java_CommentExpression = com.eagle.programmar.Java.Expressions.Java_CommentExpression;
	using Java_DotClass = com.eagle.programmar.Java.Expressions.Java_DotClass;
	using Java_ExpressionList = com.eagle.programmar.Java.Expressions.Java_ExpressionList;
	using Java_InstanceOfExpression = com.eagle.programmar.Java.Expressions.Java_InstanceOfExpression;
	using Java_InterfaceCreationWithMethod = com.eagle.programmar.Java.Expressions.Java_InterfaceCreationWithMethod;
	using Java_LambdaExpression = com.eagle.programmar.Java.Expressions.Java_LambdaExpression;
	using Java_LogicalAndExpression = com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression;
	using Java_LogicalNotExpression = com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
	using Java_LogicalOrExpression = com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression;
	using Java_MethodInvocation = com.eagle.programmar.Java.Expressions.Java_MethodInvocation;
	using Java_MultiplicativeExpression = com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression;
	using Java_NegativeExpression = com.eagle.programmar.Java.Expressions.Java_NegativeExpression;
	using Java_ParenthesizedExpression = com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression;
	using Java_PostIncrementExpression = com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression;
	using Java_PreIncrementExpression = com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression;
	using Java_RelationalExpression = com.eagle.programmar.Java.Expressions.Java_RelationalExpression;
	using Java_ShiftExpression = com.eagle.programmar.Java.Expressions.Java_ShiftExpression;
	using Java_SubfieldExpression = com.eagle.programmar.Java.Expressions.Java_SubfieldExpression;
	using Java_SubscriptExpression = com.eagle.programmar.Java.Expressions.Java_SubscriptExpression;
	using Java_TrueFalseExpression = com.eagle.programmar.Java.Expressions.Java_TrueFalseExpression;
	using Java_VariableExpression = com.eagle.programmar.Java.Expressions.Java_VariableExpression;
	using Java_MathFunction = com.eagle.programmar.Java.Functions.Java_MathFunction;
	using Java_PrintFunction = com.eagle.programmar.Java.Functions.Java_PrintFunction;
	using Java_StringFunction = com.eagle.programmar.Java.Functions.Java_StringFunction;
	using Java_EndsWithMethod = com.eagle.programmar.Java.Methods.Java_EndsWithMethod;
	using Java_EqualsMethod = com.eagle.programmar.Java.Methods.Java_EqualsMethod;
	using Java_IndexOfMethod = com.eagle.programmar.Java.Methods.Java_IndexOfMethod;
	using Java_LengthMethod = com.eagle.programmar.Java.Methods.Java_LengthMethod;
	using Java_StartsWithMethod = com.eagle.programmar.Java.Methods.Java_StartsWithMethod;
	using Java_SubstringMethod = com.eagle.programmar.Java.Methods.Java_SubstringMethod;
	using Java_ToStringMethod = com.eagle.programmar.Java.Methods.Java_ToStringMethod;
	using Java_ToUpperCaseMethod = com.eagle.programmar.Java.Methods.Java_ToUpperCaseMethod;
	using Java_TrimMethod = com.eagle.programmar.Java.Methods.Java_TrimMethod;
	using Java_BinaryNumber = com.eagle.programmar.Java.Terminals.Java_BinaryNumber;
	using Java_Character_Literal = com.eagle.programmar.Java.Terminals.Java_Character_Literal;
	using Java_HexFloatingNumber = com.eagle.programmar.Java.Terminals.Java_HexFloatingNumber;
	using Java_HexNumber = com.eagle.programmar.Java.Terminals.Java_HexNumber;
	using Java_Literal = com.eagle.programmar.Java.Terminals.Java_Literal;
	using Java_Number = com.eagle.programmar.Java.Terminals.Java_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Java_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Java_Expression() : base(_operators)
		{
		}

		public Java_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Java.Terminals.Java_HexFloatingNumber hexFloat;
		public Java_HexFloatingNumber hexFloat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Java.Terminals.Java_HexNumber hex;
		public Java_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Java.Terminals.Java_BinaryNumber binary;
		public Java_BinaryNumber binary;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.Java.Terminals.Java_Number number;
		public Java_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(50) com.eagle.programmar.Java.Terminals.Java_Literal literal;
		public Java_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(60) com.eagle.programmar.Java.Terminals.Java_Character_Literal characters;
		public Java_Character_Literal characters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(70) Java_Annotation annotation;
		public Java_Annotation annotation;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Java.Expressions.Java_ColonColonType colonColonType;
		public Java_ColonColonType colonColonType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Java.Functions.Java_MathFunction mathFunction;
		public Java_MathFunction mathFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Java.Functions.Java_StringFunction stringFunction;
		public Java_StringFunction stringFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Java.Functions.Java_PrintFunction printFunction;
		public Java_PrintFunction printFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Java.Expressions.Java_DotClass dotClass;
		public Java_DotClass dotClass;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Java.Expressions.Java_LambdaExpression lambdaExpression;
		public Java_LambdaExpression lambdaExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Java.Expressions.Java_CastExpression castExpression;
		public Java_CastExpression castExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Java.Expressions.Java_ExpressionList expressionList;
		public Java_ExpressionList expressionList;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Java.Expressions.Java_InterfaceCreationWithMethod interfaceCreationWithMethod;
		public Java_InterfaceCreationWithMethod interfaceCreationWithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Java.Expressions.Java_ClassCreationExpression classCreationExpression;
		public Java_ClassCreationExpression classCreationExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Java.Expressions.Java_ClassCreationWithInitializers classCreationWithInitializers;
		public Java_ClassCreationWithInitializers classCreationWithInitializers;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Java.Expressions.Java_ClassCreationWithSubscript classCreationWithSubscript;
		public Java_ClassCreationWithSubscript classCreationWithSubscript;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Java.Expressions.Java_MethodInvocation methodInvocation;
		public Java_MethodInvocation methodInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Java.Expressions.Java_AnnotationInvocation annotationInvocation;
		public Java_AnnotationInvocation annotationInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.Java.Expressions.Java_PreIncrementExpression preIncrementExpression;
		public Java_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.Java.Expressions.Java_PostIncrementExpression postIncrementExpression;
		public Java_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.Java.Expressions.Java_NegativeExpression negativeExpression;
		public Java_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.Java.Expressions.Java_BitwiseNotExpression logicalNotExpression;
		public Java_BitwiseNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression notExpression;
		public Java_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.Java.Expressions.Java_BuiltIn builtIn;
		public Java_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.Java.Expressions.Java_VariableExpression variableExpression;
		public Java_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(310) com.eagle.programmar.Java.Expressions.Java_ParenthesizedExpression parenthesizedExpression;
		public Java_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(320) com.eagle.programmar.Java.Expressions.Java_CommentExpression commentExpression;
		public Java_CommentExpression commentExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Java.Expressions.Java_SubscriptExpression subscriptExpression;
		public Java_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Java.Methods.Java_EqualsMethod equalsMethod;
		public Java_EqualsMethod equalsMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Java.Methods.Java_ToUpperCaseMethod toUpperCaseMethod;
		public Java_ToUpperCaseMethod toUpperCaseMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Java.Methods.Java_LengthMethod lengthMethod;
		public Java_LengthMethod lengthMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Java.Methods.Java_TrimMethod trimMethod;
		public Java_TrimMethod trimMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Java.Methods.Java_StartsWithMethod startsWithMethod;
		public Java_StartsWithMethod startsWithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Java.Methods.Java_EndsWithMethod endsWithMethod;
		public Java_EndsWithMethod endsWithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Java.Methods.Java_SubstringMethod substringMethod;
		public Java_SubstringMethod substringMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Java.Methods.Java_IndexOfMethod indexOfMethod;
		public Java_IndexOfMethod indexOfMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Java.Methods.Java_ToStringMethod toStringMethod;
		public Java_ToStringMethod toStringMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Java.Expressions.Java_SubfieldExpression subfield;
		public Java_SubfieldExpression subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Java.Expressions.Java_ColonColon colonColon;
		public Java_ColonColon colonColon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Java.Expressions.Java_ColonColonNew colonColonNew;
		public Java_ColonColonNew colonColonNew;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.Java.Expressions.Java_MultiplicativeExpression multiplicativeExpression;
		public Java_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.Java.Expressions.Java_AdditiveExpression additiveExpression;
		public Java_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1150) com.eagle.programmar.Java.Expressions.Java_ShiftExpression shiftExpression;
		public Java_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1160) com.eagle.programmar.Java.Expressions.Java_InstanceOfExpression instanceOfExpression;
		public Java_InstanceOfExpression instanceOfExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1170) com.eagle.programmar.Java.Expressions.Java_RelationalExpression relationalExpression;
		public Java_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1180) com.eagle.programmar.Java.Expressions.Java_BitwiseExpression bitwiseExpression;
		public Java_BitwiseExpression bitwiseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1190) com.eagle.programmar.Java.Expressions.Java_LogicalAndExpression logicalAndExpression;
		public Java_LogicalAndExpression logicalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1200) com.eagle.programmar.Java.Expressions.Java_LogicalOrExpression logicalOrExpression;
		public Java_LogicalOrExpression logicalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1210) com.eagle.programmar.Java.Expressions.Java_AssignmentExpression assignmentExpression;
		public Java_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1220) com.eagle.programmar.Java.Expressions.Java_TrueFalseExpression trueFalseExpression;
		public Java_TrueFalseExpression trueFalseExpression;
	}

}
