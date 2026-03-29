// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

namespace com.eagle.programmar.COBOL
{
	using COBOL_AdditiveExpression = com.eagle.programmar.COBOL.Expressions.COBOL_AdditiveExpression;
	using COBOL_AddressExpression = com.eagle.programmar.COBOL.Expressions.COBOL_AddressExpression;
	using COBOL_BuiltIn = com.eagle.programmar.COBOL.Expressions.COBOL_BuiltIn;
	using COBOL_ClassCondition = com.eagle.programmar.COBOL.Expressions.COBOL_ClassCondition;
	using COBOL_ConcatenateExpression = com.eagle.programmar.COBOL.Expressions.COBOL_ConcatenateExpression;
	using COBOL_ExponentExpression = com.eagle.programmar.COBOL.Expressions.COBOL_ExponentExpression;
	using COBOL_IsType = com.eagle.programmar.COBOL.Expressions.COBOL_IsType;
	using COBOL_LengthExpression = com.eagle.programmar.COBOL.Expressions.COBOL_LengthExpression;
	using COBOL_LinageCounterExpression = com.eagle.programmar.COBOL.Expressions.COBOL_LinageCounterExpression;
	using COBOL_LogicalAndCondition = com.eagle.programmar.COBOL.Expressions.COBOL_LogicalAndCondition;
	using COBOL_LogicalNotCondition = com.eagle.programmar.COBOL.Expressions.COBOL_LogicalNotCondition;
	using COBOL_LogicalOrCondition = com.eagle.programmar.COBOL.Expressions.COBOL_LogicalOrCondition;
	using COBOL_MultiplicativeExpression = com.eagle.programmar.COBOL.Expressions.COBOL_MultiplicativeExpression;
	using COBOL_ParenthesizedExpression = com.eagle.programmar.COBOL.Expressions.COBOL_ParenthesizedExpression;
	using COBOL_RelationCondition = com.eagle.programmar.COBOL.Expressions.COBOL_RelationCondition;
	using COBOL_SignedExpression = com.eagle.programmar.COBOL.Expressions.COBOL_SignedExpression;
	using COBOL_ThroughExpression = com.eagle.programmar.COBOL.Expressions.COBOL_ThroughExpression;
	using COBOL_VariableExpression = com.eagle.programmar.COBOL.Expressions.COBOL_VariableExpression;
	using COBOL_ExpressionFunction = com.eagle.programmar.COBOL.Functions.COBOL_ExpressionFunction;
	using COBOL_LengthFunction = com.eagle.programmar.COBOL.Functions.COBOL_LengthFunction;
	using COBOL_TrimFunction = com.eagle.programmar.COBOL.Functions.COBOL_TrimFunction;
	using COBOL_HexNumber = com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
	using COBOL_Literal = com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
	using COBOL_Number = com.eagle.programmar.COBOL.Terminals.COBOL_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class COBOL_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public COBOL_Expression() : base(_operators)
		{
		}

		public COBOL_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.COBOL.Terminals.COBOL_Literal literal;
		public COBOL_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.COBOL.Terminals.COBOL_Number number;
		public COBOL_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber hex;
		public COBOL_HexNumber hex;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.COBOL.Expressions.COBOL_LengthExpression lengthExpression;
		public COBOL_LengthExpression lengthExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.COBOL.Expressions.COBOL_AddressExpression addressExpression;
		public COBOL_AddressExpression addressExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.COBOL.Expressions.COBOL_LinageCounterExpression linageCounterExpression;
		public COBOL_LinageCounterExpression linageCounterExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.COBOL.Expressions.COBOL_BuiltIn builtIn;
		public COBOL_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.COBOL.Expressions.COBOL_ParenthesizedExpression parenthesizedExpression;
		public COBOL_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.COBOL.Functions.COBOL_LengthFunction lengthFunction;
		public COBOL_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.COBOL.Functions.COBOL_TrimFunction trimFunction;
		public COBOL_TrimFunction trimFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.COBOL.Functions.COBOL_ExpressionFunction expressionFunction;
		public COBOL_ExpressionFunction expressionFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.COBOL.Expressions.COBOL_SignedExpression signedExpression;
		public COBOL_SignedExpression signedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.COBOL.Expressions.COBOL_LogicalNotCondition notCondition;
		public COBOL_LogicalNotCondition notCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.COBOL.Expressions.COBOL_VariableExpression variableExpression;
		public COBOL_VariableExpression variableExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.COBOL.Expressions.COBOL_ThroughExpression throughExpression;
		public COBOL_ThroughExpression throughExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.COBOL.Expressions.COBOL_ConcatenateExpression concatenateExpression;
		public COBOL_ConcatenateExpression concatenateExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.COBOL.Expressions.COBOL_ExponentExpression exponentExpression;
		public COBOL_ExponentExpression exponentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.COBOL.Expressions.COBOL_MultiplicativeExpression multiplicativeExpression;
		public COBOL_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.COBOL.Expressions.COBOL_AdditiveExpression additiveExpression;
		public COBOL_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.COBOL.Expressions.COBOL_ClassCondition classCondition;
		public COBOL_ClassCondition classCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.COBOL.Expressions.COBOL_RelationCondition relationCondition;
		public COBOL_RelationCondition relationCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.COBOL.Expressions.COBOL_LogicalAndCondition andCondition;
		public COBOL_LogicalAndCondition andCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.COBOL.Expressions.COBOL_LogicalOrCondition orCondition;
		public COBOL_LogicalOrCondition orCondition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.COBOL.Expressions.COBOL_IsType isType;
		public COBOL_IsType isType;
	}

}
