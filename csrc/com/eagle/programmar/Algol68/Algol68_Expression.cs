// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.Algol68
{
	using Algol68_AdditiveExpression = com.eagle.programmar.Algol68.Expressions.Algol68_AdditiveExpression;
	using Algol68_ArrayInfo = com.eagle.programmar.Algol68.Expressions.Algol68_ArrayInfo;
	using Algol68_AssignmentExpression = com.eagle.programmar.Algol68.Expressions.Algol68_AssignmentExpression;
	using Algol68_BracketsExpression = com.eagle.programmar.Algol68.Expressions.Algol68_BracketsExpression;
	using Algol68_BuiltIn = com.eagle.programmar.Algol68.Expressions.Algol68_BuiltIn;
	using Algol68_EntierExpression = com.eagle.programmar.Algol68.Expressions.Algol68_EntierExpression;
	using Algol68_LogicalAndExpression = com.eagle.programmar.Algol68.Expressions.Algol68_LogicalAndExpression;
	using Algol68_LogicalNotExpression = com.eagle.programmar.Algol68.Expressions.Algol68_LogicalNotExpression;
	using Algol68_LogicalOrExpression = com.eagle.programmar.Algol68.Expressions.Algol68_LogicalOrExpression;
	using Algol68_MultiplicativeExpression = com.eagle.programmar.Algol68.Expressions.Algol68_MultiplicativeExpression;
	using Algol68_NegativeExpression = com.eagle.programmar.Algol68.Expressions.Algol68_NegativeExpression;
	using Algol68_ParenthesizedExpression = com.eagle.programmar.Algol68.Expressions.Algol68_ParenthesizedExpression;
	using Algol68_PostIncrementExpression = com.eagle.programmar.Algol68.Expressions.Algol68_PostIncrementExpression;
	using Algol68_Power_Expression = com.eagle.programmar.Algol68.Expressions.Algol68_Power_Expression;
	using Algol68_PreIncrementExpression = com.eagle.programmar.Algol68.Expressions.Algol68_PreIncrementExpression;
	using Algol68_ProcedureCall = com.eagle.programmar.Algol68.Expressions.Algol68_ProcedureCall;
	using Algol68_RangeExpression = com.eagle.programmar.Algol68.Expressions.Algol68_RangeExpression;
	using Algol68_RelationalExpression = com.eagle.programmar.Algol68.Expressions.Algol68_RelationalExpression;
	using Algol68_Subfield = com.eagle.programmar.Algol68.Expressions.Algol68_Subfield;
	using Algol68_SubscriptExpression = com.eagle.programmar.Algol68.Expressions.Algol68_SubscriptExpression;
	using Algol68_VariableExpression = com.eagle.programmar.Algol68.Expressions.Algol68_VariableExpression;
	using Algol68_WholeExpression = com.eagle.programmar.Algol68.Expressions.Algol68_WholeExpression;
	using Algol68_Literal = com.eagle.programmar.Algol68.Terminals.Algol68_Literal;
	using Algol68_Number = com.eagle.programmar.Algol68.Terminals.Algol68_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Algol68_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Algol68_Expression() : base(_operators)
		{
		}

		public Algol68_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Algol68.Terminals.Algol68_Number number;
		public Algol68_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Algol68.Terminals.Algol68_Literal literal;
		public Algol68_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Algol68.Expressions.Algol68_ArrayInfo arrayInfo;
		public Algol68_ArrayInfo arrayInfo;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Algol68.Expressions.Algol68_PreIncrementExpression preIncrementExpression;
		public Algol68_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Algol68.Expressions.Algol68_PostIncrementExpression postIncrementExpression;
		public Algol68_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Algol68.Expressions.Algol68_NegativeExpression negativeExpression;
		public Algol68_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Algol68.Expressions.Algol68_LogicalNotExpression logicalNotExpression;
		public Algol68_LogicalNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Algol68.Expressions.Algol68_EntierExpression entierExpression;
		public Algol68_EntierExpression entierExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Algol68.Expressions.Algol68_WholeExpression wholeExpression;
		public Algol68_WholeExpression wholeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Algol68.Expressions.Algol68_BuiltIn builtIn;
		public Algol68_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Algol68.Expressions.Algol68_ProcedureCall methodInvocation;
		public Algol68_ProcedureCall methodInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Algol68.Expressions.Algol68_VariableExpression variableExpression;
		public Algol68_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Algol68.Expressions.Algol68_BracketsExpression bracketsExpression;
		public Algol68_BracketsExpression bracketsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Algol68.Expressions.Algol68_ParenthesizedExpression parenthesizedExpression;
		public Algol68_ParenthesizedExpression parenthesizedExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Algol68.Expressions.Algol68_SubscriptExpression subscriptExpression;
		public Algol68_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Algol68.Expressions.Algol68_Subfield subfield;
		public Algol68_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Algol68.Expressions.Algol68_Power_Expression powerExpression;
		public Algol68_Power_Expression powerExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Algol68.Expressions.Algol68_MultiplicativeExpression multiplicativeExpression;
		public Algol68_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Algol68.Expressions.Algol68_AdditiveExpression additiveExpression;
		public Algol68_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Algol68.Expressions.Algol68_RelationalExpression relationalExpression;
		public Algol68_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Algol68.Expressions.Algol68_LogicalAndExpression conditionalAndExpression;
		public Algol68_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Algol68.Expressions.Algol68_LogicalOrExpression conditionalOrExpression;
		public Algol68_LogicalOrExpression conditionalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Algol68.Expressions.Algol68_AssignmentExpression assignmentExpression;
		public Algol68_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Algol68.Expressions.Algol68_RangeExpression rangeExpression;
		public Algol68_RangeExpression rangeExpression;
	}

}
