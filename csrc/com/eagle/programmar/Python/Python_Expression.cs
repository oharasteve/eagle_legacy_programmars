// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

namespace com.eagle.programmar.Python
{
	using Python_Additive_Expression = com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
	using Python_Assignment_Expression = com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
	using Python_BackQuotes = com.eagle.programmar.Python.Expressions.Python_BackQuotes;
	using Python_Bitwise_Expression = com.eagle.programmar.Python.Expressions.Python_Bitwise_Expression;
	using Python_Bitwise_Not_Expression = com.eagle.programmar.Python.Expressions.Python_Bitwise_Not_Expression;
	using Python_BracesColons = com.eagle.programmar.Python.Expressions.Python_BracesColons;
	using Python_BracesNoColons = com.eagle.programmar.Python.Expressions.Python_BracesNoColons;
	using Python_Brackets = com.eagle.programmar.Python.Expressions.Python_Brackets;
	using Python_BuiltIn = com.eagle.programmar.Python.Expressions.Python_BuiltIn;
	using Python_For_In_Expression = com.eagle.programmar.Python.Expressions.Python_For_In_Expression;
	using Python_Function_Call = com.eagle.programmar.Python.Expressions.Python_Function_Call;
	using Python_FunnyConstructor = com.eagle.programmar.Python.Expressions.Python_FunnyConstructor;
	using Python_If_Else_Expression = com.eagle.programmar.Python.Expressions.Python_If_Else_Expression;
	using Python_If_Expression = com.eagle.programmar.Python.Expressions.Python_If_Expression;
	using Python_Lambda_Expression = com.eagle.programmar.Python.Expressions.Python_Lambda_Expression;
	using Python_Literals = com.eagle.programmar.Python.Expressions.Python_Literals;
	using Python_Logical_And_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_And_Expression;
	using Python_Logical_Not_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
	using Python_Logical_Or_Expression = com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
	using Python_Multiplicative_Expression = com.eagle.programmar.Python.Expressions.Python_Multiplicative_Expression;
	using Python_Negative_Expression = com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
	using Python_Parenthesized_Expression = com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
	using Python_Power_Expression = com.eagle.programmar.Python.Expressions.Python_Power_Expression;
	using Python_RangeExpression = com.eagle.programmar.Python.Expressions.Python_RangeExpression;
	using Python_Relational_Expression = com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
	using Python_Shift_Expression = com.eagle.programmar.Python.Expressions.Python_Shift_Expression;
	using Python_StarStar_Expression = com.eagle.programmar.Python.Expressions.Python_StarStar_Expression;
	using Python_Star_Expression = com.eagle.programmar.Python.Expressions.Python_Star_Expression;
	using Python_SubfieldExpression = com.eagle.programmar.Python.Expressions.Python_SubfieldExpression;
	using Python_SubscriptExpression = com.eagle.programmar.Python.Expressions.Python_SubscriptExpression;
	using Python_VariableExpression = com.eagle.programmar.Python.Expressions.Python_VariableExpression;
	using Python_Yield = com.eagle.programmar.Python.Expressions.Python_Yield;
	using Python_Abs_Function = com.eagle.programmar.Python.Functions.Python_Abs_Function;
	using Python_Int_Function = com.eagle.programmar.Python.Functions.Python_Int_Function;
	using Python_Len_Function = com.eagle.programmar.Python.Functions.Python_Len_Function;
	using Python_Locals_Function = com.eagle.programmar.Python.Functions.Python_Locals_Function;
	using Python_Print_Function = com.eagle.programmar.Python.Functions.Python_Print_Function;
	using Python_Str_Function = com.eagle.programmar.Python.Functions.Python_Str_Function;
	using Python_EndsWith_Method = com.eagle.programmar.Python.Methods.Python_EndsWith_Method;
	using Python_Find_Method = com.eagle.programmar.Python.Methods.Python_Find_Method;
	using Python_StartsWith_Method = com.eagle.programmar.Python.Methods.Python_StartsWith_Method;
	using Python_Strip_Method = com.eagle.programmar.Python.Methods.Python_Strip_Method;
	using Python_Upper_Method = com.eagle.programmar.Python.Methods.Python_Upper_Method;
	using Python_BinaryNumber = com.eagle.programmar.Python.Terminals.Python_BinaryNumber;
	using Python_HexNumber = com.eagle.programmar.Python.Terminals.Python_HexNumber;
	using Python_Number = com.eagle.programmar.Python.Terminals.Python_Number;
	using Python_OctalNumber = com.eagle.programmar.Python.Terminals.Python_OctalNumber;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class Python_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public Python_Expression() : base(_operators)
		{
		}

		public Python_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Python.Terminals.Python_BinaryNumber binary;
		public Python_BinaryNumber binary;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Python.Terminals.Python_OctalNumber octal;
		public Python_OctalNumber octal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Python.Terminals.Python_HexNumber hex;
		public Python_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.Python.Terminals.Python_Number number;
		public Python_Number number;

		///////////////////////////////////////////////////////////////////////////
		// Primary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Python.Expressions.Python_RangeExpression rangeExpression;
		public Python_RangeExpression rangeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Python.Expressions.Python_FunnyConstructor funnyConstructor;
		public Python_FunnyConstructor funnyConstructor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression parens;
		public Python_Parenthesized_Expression parens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Python.Expressions.Python_BracesColons bracesColons;
		public Python_BracesColons bracesColons;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Python.Expressions.Python_BracesNoColons bracesNoColons;
		public Python_BracesNoColons bracesNoColons;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Python.Expressions.Python_Brackets brackets;
		public Python_Brackets brackets;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Python.Expressions.Python_Negative_Expression unarySign;
		public Python_Negative_Expression unarySign;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression logicalNotExpression;
		public Python_Logical_Not_Expression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Python.Expressions.Python_Bitwise_Not_Expression bitwiseNotExpression;
		public Python_Bitwise_Not_Expression bitwiseNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Python.Expressions.Python_Literals literals;
		public Python_Literals literals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Python.Expressions.Python_BackQuotes backQuotes;
		public Python_BackQuotes backQuotes;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Python.Functions.Python_Len_Function lenFunction;
		public Python_Len_Function lenFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Python.Functions.Python_Str_Function strFunction;
		public Python_Str_Function strFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Python.Functions.Python_Int_Function intFunction;
		public Python_Int_Function intFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.Python.Functions.Python_Abs_Function absFunction;
		public Python_Abs_Function absFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.Python.Methods.Python_Find_Method findFunction;
		public Python_Find_Method findFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.Python.Methods.Python_StartsWith_Method startsWithFunction;
		public Python_StartsWith_Method startsWithFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.Python.Methods.Python_EndsWith_Method endsWithFunction;
		public Python_EndsWith_Method endsWithFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.Python.Functions.Python_Locals_Function localsFunction;
		public Python_Locals_Function localsFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.Python.Functions.Python_Print_Function printFunction;
		public Python_Print_Function printFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.Python.Expressions.Python_Function_Call functionCall;
		public Python_Function_Call functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(310) com.eagle.programmar.Python.Expressions.Python_BuiltIn builtIn;
		public Python_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(320) com.eagle.programmar.Python.Expressions.Python_VariableExpression variableExpression;
		public Python_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(330) com.eagle.programmar.Python.Expressions.Python_Star_Expression starExpression;
		public Python_Star_Expression starExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(340) com.eagle.programmar.Python.Expressions.Python_StarStar_Expression starStarExpression;
		public Python_StarStar_Expression starStarExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(350) com.eagle.programmar.Python.Expressions.Python_Lambda_Expression lambdaExpression;
		public Python_Lambda_Expression lambdaExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(360) com.eagle.programmar.Python.Expressions.Python_Yield yield;
		public Python_Yield yield;

		///////////////////////////////////////////////////////////////////////////
		// Binary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Python.Expressions.Python_SubscriptExpression subscriptExpression;
		public Python_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Python.Methods.Python_Upper_Method upperMethod;
		public Python_Upper_Method upperMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Python.Methods.Python_Strip_Method stripMethod;
		public Python_Strip_Method stripMethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Python.Expressions.Python_SubfieldExpression subfield;
		public Python_SubfieldExpression subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Python.Expressions.Python_Power_Expression powerExpression;
		public Python_Power_Expression powerExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Python.Expressions.Python_Multiplicative_Expression multiplicativeExpression;
		public Python_Multiplicative_Expression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Python.Expressions.Python_Additive_Expression additiveExpression;
		public Python_Additive_Expression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Python.Expressions.Python_Shift_Expression shiftExpression;
		public Python_Shift_Expression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Python.Expressions.Python_Bitwise_Expression bitwiseAndExpression;
		public Python_Bitwise_Expression bitwiseAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Python.Expressions.Python_Relational_Expression relationalExpression;
		public Python_Relational_Expression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Python.Expressions.Python_Logical_And_Expression logicalAndExpression;
		public Python_Logical_And_Expression logicalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression logicalOrExpression;
		public Python_Logical_Or_Expression logicalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Python.Expressions.Python_For_In_Expression forInExpression;
		public Python_For_In_Expression forInExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.Python.Expressions.Python_If_Else_Expression ifElseExpression;
		public Python_If_Else_Expression ifElseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.Python.Expressions.Python_If_Expression ifExpression;
		public Python_If_Expression ifExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1150) com.eagle.programmar.Python.Expressions.Python_Assignment_Expression assignmentExpression;
		public Python_Assignment_Expression assignmentExpression;
	}

}
