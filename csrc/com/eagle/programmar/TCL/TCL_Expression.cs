// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL
{
	using TCL_AdditiveExpression = com.eagle.programmar.TCL.Expressions.TCL_AdditiveExpression;
	using TCL_ArrayExpression = com.eagle.programmar.TCL.Expressions.TCL_ArrayExpression;
	using TCL_BuiltIns = com.eagle.programmar.TCL.Expressions.TCL_BuiltIns;
	using TCL_LogicalAndExpression = com.eagle.programmar.TCL.Expressions.TCL_LogicalAndExpression;
	using TCL_LogicalNotExpression = com.eagle.programmar.TCL.Expressions.TCL_LogicalNotExpression;
	using TCL_LogicalOrExpression = com.eagle.programmar.TCL.Expressions.TCL_LogicalOrExpression;
	using TCL_MultiplicativeExpression = com.eagle.programmar.TCL.Expressions.TCL_MultiplicativeExpression;
	using TCL_ParenthesizedExpression = com.eagle.programmar.TCL.Expressions.TCL_ParenthesizedExpression;
	using TCL_RelationalExpression = com.eagle.programmar.TCL.Expressions.TCL_RelationalExpression;
	using TCL_SignedExpression = com.eagle.programmar.TCL.Expressions.TCL_SignedExpression;
	using TCL_VariableExpression = com.eagle.programmar.TCL.Expressions.TCL_VariableExpression;
	using TCL_BracketExpr = com.eagle.programmar.TCL.Functions.TCL_BracketExpr;
	using TCL_BracketFunction = com.eagle.programmar.TCL.Functions.TCL_BracketFunction;
	using TCL_BracketLindex = com.eagle.programmar.TCL.Functions.TCL_BracketLindex;
	using TCL_BracketStringCat = com.eagle.programmar.TCL.Functions.TCL_BracketStringCat;
	using TCL_BracketStringFirst = com.eagle.programmar.TCL.Functions.TCL_BracketStringFirst;
	using TCL_BracketStringLength = com.eagle.programmar.TCL.Functions.TCL_BracketStringLength;
	using TCL_Literal = com.eagle.programmar.TCL.Terminals.TCL_Literal;
	using TCL_Number = com.eagle.programmar.TCL.Terminals.TCL_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class TCL_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public TCL_Expression() : base(_operators)
		{
		}

		public TCL_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order.
		// The # determines operator precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.TCL.Terminals.TCL_Number number;
		public TCL_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.TCL.Terminals.TCL_Literal literal;
		public TCL_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.TCL.Expressions.TCL_SignedExpression signedExpression;
		public TCL_SignedExpression signedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.TCL.Expressions.TCL_LogicalNotExpression bangExpression;
		public TCL_LogicalNotExpression bangExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.TCL.Expressions.TCL_BuiltIns builtIn;
		public TCL_BuiltIns builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.TCL.Expressions.TCL_VariableExpression variableExpression;
		public TCL_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.TCL.Expressions.TCL_ParenthesizedExpression parenthesizedExpression;
		public TCL_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.TCL.Expressions.TCL_ArrayExpression arrayExpression;
		public TCL_ArrayExpression arrayExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.TCL.Functions.TCL_BracketExpr exprExpression;
		public TCL_BracketExpr exprExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.TCL.Functions.TCL_BracketLindex lindexExpression;
		public TCL_BracketLindex lindexExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.TCL.Functions.TCL_BracketStringFirst stringFirstExpression;
		public TCL_BracketStringFirst stringFirstExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.TCL.Functions.TCL_BracketStringLength stringLengthExpression;
		public TCL_BracketStringLength stringLengthExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.TCL.Functions.TCL_BracketStringCat stringCatExpression;
		public TCL_BracketStringCat stringCatExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.TCL.Functions.TCL_BracketFunction functionCall;
		public TCL_BracketFunction functionCall;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.TCL.Expressions.TCL_MultiplicativeExpression multiplicativeExpression;
		public TCL_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.TCL.Expressions.TCL_AdditiveExpression additiveExpression;
		public TCL_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.TCL.Expressions.TCL_RelationalExpression relationalExpression;
		public TCL_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.TCL.Expressions.TCL_LogicalAndExpression conditionalAndExpression;
		public TCL_LogicalAndExpression conditionalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.TCL.Expressions.TCL_LogicalOrExpression conditionalOrExpression;
		public TCL_LogicalOrExpression conditionalOrExpression;
	}

}
