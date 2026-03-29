// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK
{
	using AWK_AdditiveExpression = com.eagle.programmar.AWK.Expressions.AWK_AdditiveExpression;
	using AWK_AssignmentExpression = com.eagle.programmar.AWK.Expressions.AWK_AssignmentExpression;
	using AWK_CallFunction = com.eagle.programmar.AWK.Expressions.AWK_CallFunction;
	using AWK_ConcatenationExpression = com.eagle.programmar.AWK.Expressions.AWK_ConcatenationExpression;
	using AWK_DollarParensExpression = com.eagle.programmar.AWK.Expressions.AWK_DollarParensExpression;
	using AWK_GetLine = com.eagle.programmar.AWK.Expressions.AWK_GetLine;
	using AWK_InExpression = com.eagle.programmar.AWK.Expressions.AWK_InExpression;
	using AWK_LogicalAndExpression = com.eagle.programmar.AWK.Expressions.AWK_LogicalAndExpression;
	using AWK_LogicalNotExpression = com.eagle.programmar.AWK.Expressions.AWK_LogicalNotExpression;
	using AWK_LogicalOrExpression = com.eagle.programmar.AWK.Expressions.AWK_LogicalOrExpression;
	using AWK_MultiplicativeExpression = com.eagle.programmar.AWK.Expressions.AWK_MultiplicativeExpression;
	using AWK_NegativeExpression = com.eagle.programmar.AWK.Expressions.AWK_NegativeExpression;
	using AWK_ParenthesizedExpression = com.eagle.programmar.AWK.Expressions.AWK_ParenthesizedExpression;
	using AWK_PatternExpression = com.eagle.programmar.AWK.Expressions.AWK_PatternExpression;
	using AWK_PostIncrementExpression = com.eagle.programmar.AWK.Expressions.AWK_PostIncrementExpression;
	using AWK_PowerExpression = com.eagle.programmar.AWK.Expressions.AWK_PowerExpression;
	using AWK_PreIncrementExpression = com.eagle.programmar.AWK.Expressions.AWK_PreIncrementExpression;
	using AWK_RegularExpression = com.eagle.programmar.AWK.Expressions.AWK_RegularExpression;
	using AWK_RelationalExpression = com.eagle.programmar.AWK.Expressions.AWK_RelationalExpression;
	using AWK_String = com.eagle.programmar.AWK.Expressions.AWK_String;
	using AWK_SubscriptExpression = com.eagle.programmar.AWK.Expressions.AWK_SubscriptExpression;
	using AWK_TrueFalseExpression = com.eagle.programmar.AWK.Expressions.AWK_TrueFalseExpression;
	using AWK_VariableExpression = com.eagle.programmar.AWK.Expressions.AWK_VariableExpression;
	using AWK_BuiltinFunction = com.eagle.programmar.AWK.Functions.AWK_BuiltinFunction;
	using AWK_IntFunction = com.eagle.programmar.AWK.Functions.AWK_IntFunction;
	using AWK_LengthFunction = com.eagle.programmar.AWK.Functions.AWK_LengthFunction;
	using AWK_SprintfFunction = com.eagle.programmar.AWK.Functions.AWK_SprintfFunction;
	using AWK_SubstrFunction = com.eagle.programmar.AWK.Functions.AWK_SubstrFunction;
	using AWK_Number = com.eagle.programmar.AWK.Terminals.AWK_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class AWK_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public AWK_Expression() : base(_operators)
		{
		}

		public AWK_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.AWK.Terminals.AWK_Number number;
		public AWK_Number number;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.AWK.Expressions.AWK_GetLine getLine;
		public AWK_GetLine getLine;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.AWK.Expressions.AWK_CallFunction userFunction;
		public AWK_CallFunction userFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.AWK.Expressions.AWK_PatternExpression patternExpression;
		public AWK_PatternExpression patternExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.AWK.Expressions.AWK_PreIncrementExpression preIncrementExpression;
		public AWK_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.AWK.Expressions.AWK_PostIncrementExpression postIncrementExpression;
		public AWK_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.AWK.Expressions.AWK_NegativeExpression negativeExpression;
		public AWK_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.AWK.Expressions.AWK_LogicalNotExpression notExpression;
		public AWK_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.AWK.Expressions.AWK_ConcatenationExpression concateationExpression;
		public AWK_ConcatenationExpression concateationExpression; // Has to precede vars and strings
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.AWK.Expressions.AWK_VariableExpression variableExpression;
		public AWK_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.AWK.Expressions.AWK_String string;
		public AWK_String @string;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.AWK.Functions.AWK_IntFunction intFunction;
		public AWK_IntFunction intFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.AWK.Functions.AWK_LengthFunction lengthFunction;
		public AWK_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.AWK.Functions.AWK_SprintfFunction sprintfFunction;
		public AWK_SprintfFunction sprintfFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.AWK.Functions.AWK_SubstrFunction substrFunction;
		public AWK_SubstrFunction substrFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.AWK.Functions.AWK_BuiltinFunction builtinFunction;
		public AWK_BuiltinFunction builtinFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.AWK.Expressions.AWK_ParenthesizedExpression parenthesizedExpression;
		public AWK_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.AWK.Expressions.AWK_DollarParensExpression dollarParensExpression;
		public AWK_DollarParensExpression dollarParensExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.AWK.Expressions.AWK_SubscriptExpression subscriptExpression;
		public AWK_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.AWK.Expressions.AWK_PowerExpression powerExpression;
		public AWK_PowerExpression powerExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.AWK.Expressions.AWK_MultiplicativeExpression multiplicativeExpression;
		public AWK_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.AWK.Expressions.AWK_AdditiveExpression additiveExpression;
		public AWK_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.AWK.Expressions.AWK_RelationalExpression relationalExpression;
		public AWK_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.AWK.Expressions.AWK_RegularExpression regularExpression;
		public AWK_RegularExpression regularExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.AWK.Expressions.AWK_InExpression inExpression;
		public AWK_InExpression inExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.AWK.Expressions.AWK_LogicalAndExpression andExpression;
		public AWK_LogicalAndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.AWK.Expressions.AWK_LogicalOrExpression orExpression;
		public AWK_LogicalOrExpression orExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.AWK.Expressions.AWK_TrueFalseExpression trueFalseExpression;
		public AWK_TrueFalseExpression trueFalseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.AWK.Expressions.AWK_AssignmentExpression assignmentExpression;
		public AWK_AssignmentExpression assignmentExpression;
	}

}
