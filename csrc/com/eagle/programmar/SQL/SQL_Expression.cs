// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL
{
	using SQL_AdditiveExpression = com.eagle.programmar.SQL.Expressions.SQL_AdditiveExpression;
	using SQL_Builtin = com.eagle.programmar.SQL.Expressions.SQL_Builtin;
	using SQL_CastExpression = com.eagle.programmar.SQL.Expressions.SQL_CastExpression;
	using SQL_CurrentTimeStamp = com.eagle.programmar.SQL.Expressions.SQL_CurrentTimeStamp;
	using SQL_DollarVariable = com.eagle.programmar.SQL.Expressions.SQL_DollarVariable;
	using SQL_FunctionCall = com.eagle.programmar.SQL.Expressions.SQL_FunctionCall;
	using SQL_InnerSelect = com.eagle.programmar.SQL.Expressions.SQL_InnerSelect;
	using SQL_InnerValues = com.eagle.programmar.SQL.Expressions.SQL_InnerValues;
	using SQL_InnerWith = com.eagle.programmar.SQL.Expressions.SQL_InnerWith;
	using SQL_LogicalAndExpression = com.eagle.programmar.SQL.Expressions.SQL_LogicalAndExpression;
	using SQL_LogicalNotExpression = com.eagle.programmar.SQL.Expressions.SQL_LogicalNotExpression;
	using SQL_LogicalOrExpression = com.eagle.programmar.SQL.Expressions.SQL_LogicalOrExpression;
	using SQL_MultiplicativeExpression = com.eagle.programmar.SQL.Expressions.SQL_MultiplicativeExpression;
	using SQL_NegativeExpression = com.eagle.programmar.SQL.Expressions.SQL_NegativeExpression;
	using SQL_Parentheses = com.eagle.programmar.SQL.Expressions.SQL_Parentheses;
	using SQL_RelationalExpression = com.eagle.programmar.SQL.Expressions.SQL_RelationalExpression;
	using SQL_Star = com.eagle.programmar.SQL.Expressions.SQL_Star;
	using SQL_VariableExpression = com.eagle.programmar.SQL.Expressions.SQL_VariableExpression;
	using SQL_BuiltinFunction = com.eagle.programmar.SQL.Functions.SQL_BuiltinFunction;
	using SQL_ConcatFunction = com.eagle.programmar.SQL.Functions.SQL_ConcatFunction;
	using SQL_LeftFunction = com.eagle.programmar.SQL.Functions.SQL_LeftFunction;
	using SQL_LengthFunction = com.eagle.programmar.SQL.Functions.SQL_LengthFunction;
	using SQL_SubstringFunction = com.eagle.programmar.SQL.Functions.SQL_SubstringFunction;
	using SQL_HexString = com.eagle.programmar.SQL.Terminals.SQL_HexString;
	using SQL_Literal = com.eagle.programmar.SQL.Terminals.SQL_Literal;
	using SQL_Number = com.eagle.programmar.SQL.Terminals.SQL_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class SQL_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public SQL_Expression() : base(_operators)
		{
		}

		public SQL_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.SQL.Terminals.SQL_Number number;
		public SQL_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.SQL.Terminals.SQL_Literal literal;
		public SQL_Literal literal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.SQL.Terminals.SQL_HexString hex;
		public SQL_HexString hex;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.SQL.Expressions.SQL_CurrentTimeStamp currentTimeStamp;
		public SQL_CurrentTimeStamp currentTimeStamp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.SQL.Expressions.SQL_NegativeExpression negativeExpression;
		public SQL_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.SQL.Expressions.SQL_LogicalNotExpression notExpression;
		public SQL_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.SQL.Expressions.SQL_Builtin builtin;
		public SQL_Builtin builtin;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.SQL.Functions.SQL_ConcatFunction concatFunction;
		public SQL_ConcatFunction concatFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.SQL.Functions.SQL_LeftFunction leftFunction;
		public SQL_LeftFunction leftFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.SQL.Functions.SQL_LengthFunction lengthFunction;
		public SQL_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.SQL.Functions.SQL_SubstringFunction substringFunction;
		public SQL_SubstringFunction substringFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.SQL.Functions.SQL_BuiltinFunction functionCall;
		public SQL_BuiltinFunction functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.SQL.Expressions.SQL_FunctionCall functionCallExpression;
		public SQL_FunctionCall functionCallExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.SQL.Expressions.SQL_CastExpression castExpression;
		public SQL_CastExpression castExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.SQL.Expressions.SQL_DollarVariable dollarVariable;
		public SQL_DollarVariable dollarVariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.SQL.Expressions.SQL_VariableExpression variableExpression;
		public SQL_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.SQL.Expressions.SQL_Star star;
		public SQL_Star star;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.SQL.Expressions.SQL_InnerSelect innerSelect;
		public SQL_InnerSelect innerSelect;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.SQL.Expressions.SQL_InnerValues innerValues;
		public SQL_InnerValues innerValues;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.SQL.Expressions.SQL_InnerWith innerWith;
		public SQL_InnerWith innerWith;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.SQL.Expressions.SQL_Parentheses parentheses;
		public SQL_Parentheses parentheses;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.SQL.Expressions.SQL_MultiplicativeExpression multiplicativeExpression;
		public SQL_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.SQL.Expressions.SQL_AdditiveExpression additiveExpression;
		public SQL_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.SQL.Expressions.SQL_RelationalExpression relationalExpression;
		public SQL_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.SQL.Expressions.SQL_LogicalAndExpression andExpression;
		public SQL_LogicalAndExpression andExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.SQL.Expressions.SQL_LogicalOrExpression orExpression;
		public SQL_LogicalOrExpression orExpression;
	}

}
