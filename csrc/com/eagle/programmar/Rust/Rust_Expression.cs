// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust
{
	using Rust_AdditiveExpression = com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
	using Rust_AsExpression = com.eagle.programmar.Rust.Expressions.Rust_AsExpression;
	using Rust_AssignmentExpression = com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
	using Rust_BitwiseExpression = com.eagle.programmar.Rust.Expressions.Rust_BitwiseExpression;
	using Rust_BorrowExpression = com.eagle.programmar.Rust.Expressions.Rust_BorrowExpression;
	using Rust_BuiltIn = com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
	using Rust_CastExpression = com.eagle.programmar.Rust.Expressions.Rust_CastExpression;
	using Rust_ClassCreationExpression = com.eagle.programmar.Rust.Expressions.Rust_ClassCreationExpression;
	using Rust_ExpressionArray = com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray;
	using Rust_LogicalAndExpression = com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression;
	using Rust_LogicalOrExpression = com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression;
	using Rust_MethodInvocation = com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
	using Rust_MultiplicativeExpression = com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
	using Rust_NegativeExpression = com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression;
	using Rust_NotExpression = com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
	using Rust_ParenthesizedExpression = com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
	using Rust_RangeExpression = com.eagle.programmar.Rust.Expressions.Rust_RangeExpression;
	using Rust_RelationalExpression = com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
	using Rust_ShiftExpression = com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression;
	using Rust_Subfield = com.eagle.programmar.Rust.Expressions.Rust_Subfield;
	using Rust_SubscriptExpression = com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
	using Rust_TypeExpression = com.eagle.programmar.Rust.Expressions.Rust_TypeExpression;
	using Rust_Underscore = com.eagle.programmar.Rust.Expressions.Rust_Underscore;
	using Rust_VariableExpression = com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
	using Rust_AbsMethod = com.eagle.programmar.Rust.Functions.Rust_AbsMethod;
	using Rust_FindMethod = com.eagle.programmar.Rust.Functions.Rust_FindMethod;
	using Rust_FormatFunction = com.eagle.programmar.Rust.Functions.Rust_FormatFunction;
	using Rust_LenMethod = com.eagle.programmar.Rust.Functions.Rust_LenMethod;
	using Rust_PowMethod = com.eagle.programmar.Rust.Functions.Rust_PowMethod;
	using Rust_PrintlnFunction = com.eagle.programmar.Rust.Functions.Rust_PrintlnFunction;
	using Rust_RevMethod = com.eagle.programmar.Rust.Functions.Rust_RevMethod;
	using Rust_StartsWithMethod = com.eagle.programmar.Rust.Functions.Rust_StartsWithMethod;
	using Rust_ToStringMethod = com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
	using Rust_TrimMethod = com.eagle.programmar.Rust.Functions.Rust_TrimMethod;
	using Rust_BinaryNumber = com.eagle.programmar.Rust.Terminals.Rust_BinaryNumber;
	using Rust_Character_Literal = com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
	using Rust_HexNumber = com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
	using Rust_Literal = com.eagle.programmar.Rust.Terminals.Rust_Literal;
	using Rust_Number = com.eagle.programmar.Rust.Terminals.Rust_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Rust_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Rust_Expression() : base(_operators)
		{
		}

		public Rust_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Rust.Terminals.Rust_BinaryNumber bin;
		public Rust_BinaryNumber bin;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Rust.Terminals.Rust_HexNumber hex;
		public Rust_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Rust.Terminals.Rust_Number number;
		public Rust_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.Rust.Terminals.Rust_Literal literal;
		public Rust_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(50) com.eagle.programmar.Rust.Terminals.Rust_Character_Literal characters;
		public Rust_Character_Literal characters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(60) com.eagle.programmar.Rust.Expressions.Rust_Underscore underscore;
		public Rust_Underscore underscore;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation methodInvocation;
		public Rust_MethodInvocation methodInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression negativeExpression;
		public Rust_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Rust.Functions.Rust_AbsMethod absMethod;
		public Rust_AbsMethod absMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Rust.Expressions.Rust_NotExpression notExpression;
		public Rust_NotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Rust.Expressions.Rust_BuiltIn builtIn;
		public Rust_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Rust.Expressions.Rust_VariableExpression variableExpression;
		public Rust_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Rust.Expressions.Rust_CastExpression castExpression;
		public Rust_CastExpression castExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Rust.Functions.Rust_FormatFunction builtinFunction;
		public Rust_FormatFunction builtinFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression parenthesizedExpression;
		public Rust_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray expressionArray;
		public Rust_ExpressionArray expressionArray;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Rust.Expressions.Rust_BorrowExpression borrowExpression;
		public Rust_BorrowExpression borrowExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Rust.Functions.Rust_PrintlnFunction printlnStatement;
		public Rust_PrintlnFunction printlnStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Rust.Expressions.Rust_TypeExpression typeExpression;
		public Rust_TypeExpression typeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Rust.Expressions.Rust_ClassCreationExpression createExpression;
		public Rust_ClassCreationExpression createExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression subscriptExpression;
		public Rust_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Rust.Functions.Rust_LenMethod lenMethod;
		public Rust_LenMethod lenMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Rust.Functions.Rust_PowMethod powMethod;
		public Rust_PowMethod powMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Rust.Functions.Rust_RevMethod revMethod;
		public Rust_RevMethod revMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Rust.Functions.Rust_StartsWithMethod startsWithMethod;
		public Rust_StartsWithMethod startsWithMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Rust.Functions.Rust_ToStringMethod toStringMethod;
		public Rust_ToStringMethod toStringMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Rust.Functions.Rust_TrimMethod trimMethod;
		public Rust_TrimMethod trimMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Rust.Functions.Rust_FindMethod findMethod;
		public Rust_FindMethod findMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Rust.Expressions.Rust_Subfield subfield;
		public Rust_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression multiplicativeExpression;
		public Rust_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression additiveExpression;
		public Rust_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression shiftExpression;
		public Rust_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression relationalExpression;
		public Rust_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.Rust.Expressions.Rust_BitwiseExpression bitwiseExpression;
		public Rust_BitwiseExpression bitwiseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression conditionalAndExpression;
		public Rust_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1150) com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression conditionalOrExpression;
		public Rust_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1160) com.eagle.programmar.Rust.Expressions.Rust_RangeExpression rangeExpression;
		public Rust_RangeExpression rangeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1170) com.eagle.programmar.Rust.Expressions.Rust_AsExpression asExpression;
		public Rust_AsExpression asExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1180) com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression asgExpression;
		public Rust_AssignmentExpression asgExpression;
	}

}
