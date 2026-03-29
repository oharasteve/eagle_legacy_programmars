// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

namespace com.eagle.programmar.VB
{
	using VB_AdditiveExpression = com.eagle.programmar.VB.Expressions.VB_AdditiveExpression;
	using VB_ArrayExpression = com.eagle.programmar.VB.Expressions.VB_ArrayExpression;
	using VB_BitwiseAndExpression = com.eagle.programmar.VB.Expressions.VB_BitwiseAndExpression;
	using VB_BuiltIn = com.eagle.programmar.VB.Expressions.VB_BuiltIn;
	using VB_CommentExpression = com.eagle.programmar.VB.Expressions.VB_CommentExpression;
	using VB_ConcatExpression = com.eagle.programmar.VB.Expressions.VB_ConcatExpression;
	using VB_EqualityExpression = com.eagle.programmar.VB.Expressions.VB_EqualityExpression;
	using VB_ExponentExpression = com.eagle.programmar.VB.Expressions.VB_ExponentExpression;
	using VB_FunctionCall = com.eagle.programmar.VB.Expressions.VB_FunctionCall;
	using VB_InstanceOfExpression = com.eagle.programmar.VB.Expressions.VB_InstanceOfExpression;
	using VB_LogicalAndExpression = com.eagle.programmar.VB.Expressions.VB_LogicalAndExpression;
	using VB_LogicalNotExpression = com.eagle.programmar.VB.Expressions.VB_LogicalNotExpression;
	using VB_LogicalOrExpression = com.eagle.programmar.VB.Expressions.VB_LogicalOrExpression;
	using VB_LogicalXorExpression = com.eagle.programmar.VB.Expressions.VB_LogicalXorExpression;
	using VB_MultiplicativeExpression = com.eagle.programmar.VB.Expressions.VB_MultiplicativeExpression;
	using VB_NegativeExpression = com.eagle.programmar.VB.Expressions.VB_NegativeExpression;
	using VB_ParenthesizedExpression = com.eagle.programmar.VB.Expressions.VB_ParenthesizedExpression;
	using VB_RelationalExpression = com.eagle.programmar.VB.Expressions.VB_RelationalExpression;
	using VB_ShiftExpression = com.eagle.programmar.VB.Expressions.VB_ShiftExpression;
	using VB_Subfield = com.eagle.programmar.VB.Expressions.VB_Subfield;
	using VB_VariableExpression = com.eagle.programmar.VB.Expressions.VB_VariableExpression;
	using VB_CStrFunction = com.eagle.programmar.VB.Functions.VB_CStrFunction;
	using VB_LenFunction = com.eagle.programmar.VB.Functions.VB_LenFunction;
	using VB_MidFunction = com.eagle.programmar.VB.Functions.VB_MidFunction;
	using VB_UcaseFunction = com.eagle.programmar.VB.Functions.VB_UcaseFunction;
	using VB_Literal = com.eagle.programmar.VB.Terminals.VB_Literal;
	using VB_Number = com.eagle.programmar.VB.Terminals.VB_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class VB_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public VB_Expression() : base(_operators)
		{
		}

		public VB_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.VB.Terminals.VB_Number number;
		public VB_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.VB.Terminals.VB_Literal literal;
		public VB_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.VB.Expressions.VB_ArrayExpression arrayExpression;
		public VB_ArrayExpression arrayExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.VB.Expressions.VB_BuiltIn builtIn;
		public VB_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.VB.Functions.VB_MidFunction midFunction;
		public VB_MidFunction midFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.VB.Functions.VB_LenFunction lenFunction;
		public VB_LenFunction lenFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.VB.Functions.VB_UcaseFunction ucaseFunction;
		public VB_UcaseFunction ucaseFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.VB.Functions.VB_CStrFunction cstrFunction;
		public VB_CStrFunction cstrFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.VB.Expressions.VB_FunctionCall functionCall;
		public VB_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.VB.Expressions.VB_NegativeExpression negativeExpression;
		public VB_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.VB.Expressions.VB_LogicalNotExpression notExpression;
		public VB_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.VB.Expressions.VB_VariableExpression variableExpression;
		public VB_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.VB.Expressions.VB_ParenthesizedExpression parenthesizedExpression;
		public VB_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.VB.Expressions.VB_CommentExpression commentExpression;
		public VB_CommentExpression commentExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.VB.Expressions.VB_Subfield subfield;
		public VB_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.VB.Expressions.VB_ExponentExpression exponentExpression;
		public VB_ExponentExpression exponentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.VB.Expressions.VB_MultiplicativeExpression multiplicativeExpression;
		public VB_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.VB.Expressions.VB_AdditiveExpression additiveExpression;
		public VB_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.VB.Expressions.VB_ConcatExpression concatExpression;
		public VB_ConcatExpression concatExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.VB.Expressions.VB_ShiftExpression shiftExpression;
		public VB_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.VB.Expressions.VB_RelationalExpression relationalExpression;
		public VB_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.VB.Expressions.VB_InstanceOfExpression instanceOfExpression;
		public VB_InstanceOfExpression instanceOfExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.VB.Expressions.VB_EqualityExpression equalityExpression;
		public VB_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.VB.Expressions.VB_BitwiseAndExpression andExpression;
		public VB_BitwiseAndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.VB.Expressions.VB_LogicalXorExpression inclusiveOrExpression;
		public VB_LogicalXorExpression inclusiveOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.VB.Expressions.VB_LogicalAndExpression conditionalAndExpression;
		public VB_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.VB.Expressions.VB_LogicalOrExpression conditionalOrExpression;
		public VB_LogicalOrExpression conditionalOrExpression;
	}

}
