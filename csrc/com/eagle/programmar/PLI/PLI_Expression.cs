// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

namespace com.eagle.programmar.PLI
{
	using PLI_AdditiveExpression = com.eagle.programmar.PLI.Expressions.PLI_AdditiveExpression;
	using PLI_CommentExpression = com.eagle.programmar.PLI.Expressions.PLI_CommentExpression;
	using PLI_ExponentExpression = com.eagle.programmar.PLI.Expressions.PLI_ExponentExpression;
	using PLI_FieldReference = com.eagle.programmar.PLI.Expressions.PLI_FieldReference;
	using PLI_LogicalAndExpression = com.eagle.programmar.PLI.Expressions.PLI_LogicalAndExpression;
	using PLI_LogicalAndThenExpression = com.eagle.programmar.PLI.Expressions.PLI_LogicalAndThenExpression;
	using PLI_LogicalNotExpression = com.eagle.programmar.PLI.Expressions.PLI_LogicalNotExpression;
	using PLI_LogicalOrElseExpression = com.eagle.programmar.PLI.Expressions.PLI_LogicalOrElseExpression;
	using PLI_LogicalOrExpression = com.eagle.programmar.PLI.Expressions.PLI_LogicalOrExpression;
	using PLI_MultiplicativeExpression = com.eagle.programmar.PLI.Expressions.PLI_MultiplicativeExpression;
	using PLI_NegativeExpression = com.eagle.programmar.PLI.Expressions.PLI_NegativeExpression;
	using PLI_ParenthesizedExpression = com.eagle.programmar.PLI.Expressions.PLI_ParenthesizedExpression;
	using PLI_RelationalExpression = com.eagle.programmar.PLI.Expressions.PLI_RelationalExpression;
	using PLI_RepeatedBitLiteral = com.eagle.programmar.PLI.Expressions.PLI_RepeatedBitLiteral;
	using PLI_RepeatedHexLiteral = com.eagle.programmar.PLI.Expressions.PLI_RepeatedHexLiteral;
	using PLI_RepeatedLiteral = com.eagle.programmar.PLI.Expressions.PLI_RepeatedLiteral;
	using PLI_StrCatExpression = com.eagle.programmar.PLI.Expressions.PLI_StrCatExpression;
	using PLI_VariableOrFunctionCall = com.eagle.programmar.PLI.Expressions.PLI_VariableOrFunctionCall;
	using PLI_LengthFunction = com.eagle.programmar.PLI.Functions.PLI_LengthFunction;
	using PLI_ModFunction = com.eagle.programmar.PLI.Functions.PLI_ModFunction;
	using PLI_SubstrFunction = com.eagle.programmar.PLI.Functions.PLI_SubstrFunction;
	using PLI_TrimFunction = com.eagle.programmar.PLI.Functions.PLI_TrimFunction;
	using PLI_TruncFunction = com.eagle.programmar.PLI.Functions.PLI_TruncFunction;
	using PLI_BitLiteral = com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
	using PLI_HexNumber = com.eagle.programmar.PLI.Terminals.PLI_HexNumber;
	using PLI_Literal = com.eagle.programmar.PLI.Terminals.PLI_Literal;
	using PLI_Number = com.eagle.programmar.PLI.Terminals.PLI_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class PLI_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public PLI_Expression() : base(_operators)
		{
		}

		public PLI_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.PLI.Terminals.PLI_Number number;
		public PLI_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.PLI.Terminals.PLI_BitLiteral bits;
		public PLI_BitLiteral bits;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.PLI.Terminals.PLI_HexNumber hex;
		public PLI_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.PLI.Terminals.PLI_Literal literal;
		public PLI_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.PLI.Expressions.PLI_RepeatedBitLiteral repeatedBitLiteral;
		public PLI_RepeatedBitLiteral repeatedBitLiteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.PLI.Expressions.PLI_RepeatedHexLiteral repeatedHexLiteral;
		public PLI_RepeatedHexLiteral repeatedHexLiteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.PLI.Expressions.PLI_RepeatedLiteral repeatedLiteral;
		public PLI_RepeatedLiteral repeatedLiteral;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.PLI.Expressions.PLI_NegativeExpression negativeExpression;
		public PLI_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.PLI.Expressions.PLI_LogicalNotExpression notExpression;
		public PLI_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.PLI.Expressions.PLI_FieldReference fieldReference;
		public PLI_FieldReference fieldReference;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.PLI.Functions.PLI_LengthFunction lengthFunction;
		public PLI_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.PLI.Functions.PLI_ModFunction modFunction;
		public PLI_ModFunction modFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.PLI.Functions.PLI_SubstrFunction substrFunction;
		public PLI_SubstrFunction substrFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.PLI.Functions.PLI_TrimFunction trimFunction;
		public PLI_TrimFunction trimFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.PLI.Functions.PLI_TruncFunction truncFunction;
		public PLI_TruncFunction truncFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.PLI.Expressions.PLI_VariableOrFunctionCall variableOrFunctionCall;
		public PLI_VariableOrFunctionCall variableOrFunctionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.PLI.Expressions.PLI_ParenthesizedExpression parenthesizedExpression;
		public PLI_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.PLI.Expressions.PLI_CommentExpression commentExpression;
		public PLI_CommentExpression commentExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.PLI.Expressions.PLI_ExponentExpression exponentExpression;
		public PLI_ExponentExpression exponentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.PLI.Expressions.PLI_MultiplicativeExpression multiplicativeExpression;
		public PLI_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.PLI.Expressions.PLI_AdditiveExpression additiveExpression;
		public PLI_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.PLI.Expressions.PLI_StrCatExpression strCatExpression;
		public PLI_StrCatExpression strCatExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.PLI.Expressions.PLI_RelationalExpression relationalExpression;
		public PLI_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.PLI.Expressions.PLI_LogicalAndExpression andExpression;
		public PLI_LogicalAndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.PLI.Expressions.PLI_LogicalOrExpression orExpression;
		public PLI_LogicalOrExpression orExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.PLI.Expressions.PLI_LogicalAndThenExpression andThenExpression;
		public PLI_LogicalAndThenExpression andThenExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.PLI.Expressions.PLI_LogicalOrElseExpression orElseExpression;
		public PLI_LogicalOrElseExpression orElseExpression;
	}

}
