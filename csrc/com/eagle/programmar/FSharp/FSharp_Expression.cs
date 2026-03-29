// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp
{
	using FSharp_Additive_Expression = com.eagle.programmar.FSharp.Expressions.FSharp_Additive_Expression;
	using FSharp_BracketBars = com.eagle.programmar.FSharp.Expressions.FSharp_BracketBars;
	using FSharp_BuiltIn = com.eagle.programmar.FSharp.Expressions.FSharp_BuiltIn;
	using FSharp_FunctionCall = com.eagle.programmar.FSharp.Expressions.FSharp_FunctionCall;
	using FSharp_Logical_And_Expression = com.eagle.programmar.FSharp.Expressions.FSharp_Logical_And_Expression;
	using FSharp_Logical_Not_Expresion = com.eagle.programmar.FSharp.Expressions.FSharp_Logical_Not_Expresion;
	using FSharp_Logical_Or_Expression = com.eagle.programmar.FSharp.Expressions.FSharp_Logical_Or_Expression;
	using FSharp_Multiplicative_Expression = com.eagle.programmar.FSharp.Expressions.FSharp_Multiplicative_Expression;
	using FSharp_Parens = com.eagle.programmar.FSharp.Expressions.FSharp_Parens;
	using FSharp_Range_Expression = com.eagle.programmar.FSharp.Expressions.FSharp_Range_Expression;
	using FSharp_Relational_Expression = com.eagle.programmar.FSharp.Expressions.FSharp_Relational_Expression;
	using FSharp_Subfield = com.eagle.programmar.FSharp.Expressions.FSharp_Subfield;
	using FSharp_SubscriptExpression = com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression;
	using FSharp_UnarySign = com.eagle.programmar.FSharp.Expressions.FSharp_UnarySign;
	using FSharp_VariableExpression = com.eagle.programmar.FSharp.Expressions.FSharp_VariableExpression;
	using FSharp_LengthFunction = com.eagle.programmar.FSharp.Functions.FSharp_LengthFunction;
	using FSharp_StartsWithFunction = com.eagle.programmar.FSharp.Functions.FSharp_StartsWithFunction;
	using FSharp_Literal = com.eagle.programmar.FSharp.Terminals.FSharp_Literal;
	using FSharp_Number = com.eagle.programmar.FSharp.Terminals.FSharp_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;

	public class FSharp_Expression : PrecedenceChooser, AbstractExpression
	{
		private static OperatorList _operators = new OperatorList();

		public FSharp_Expression() : base(_operators)
		{
		}

		public FSharp_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order.
		// The # determines operator precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.FSharp.Terminals.FSharp_Number number;
		public FSharp_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.FSharp.Terminals.FSharp_Literal literal;
		public FSharp_Literal literal;

		///////////////////////////////////////////////////////////////////////////
		// Primary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.FSharp.Expressions.FSharp_BracketBars bracketBars;
		public FSharp_BracketBars bracketBars;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.FSharp.Expressions.FSharp_Parens parens;
		public FSharp_Parens parens;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.FSharp.Expressions.FSharp_FunctionCall functionCall;
		public FSharp_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.FSharp.Expressions.FSharp_UnarySign unarySign;
		public FSharp_UnarySign unarySign;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.FSharp.Expressions.FSharp_Logical_Not_Expresion notOper;
		public FSharp_Logical_Not_Expresion notOper;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.FSharp.Expressions.FSharp_BuiltIn builtIn;
		public FSharp_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.FSharp.Expressions.FSharp_VariableExpression variableExpression;
		public FSharp_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.FSharp.Expressions.FSharp_Range_Expression rangeExpression;
		public FSharp_Range_Expression rangeExpression;

		///////////////////////////////////////////////////////////////////////////
		// Binary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.FSharp.Expressions.FSharp_SubscriptExpression subscriptExpression;
		public FSharp_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.FSharp.Functions.FSharp_StartsWithFunction startswithFunction;
		public FSharp_StartsWithFunction startswithFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.FSharp.Functions.FSharp_LengthFunction lengthFunction;
		public FSharp_LengthFunction lengthFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.FSharp.Expressions.FSharp_Subfield subfield;
		public FSharp_Subfield subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.FSharp.Expressions.FSharp_Multiplicative_Expression multiplicative_Expression;
		public FSharp_Multiplicative_Expression multiplicative_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.FSharp.Expressions.FSharp_Additive_Expression additive_Expression;
		public FSharp_Additive_Expression additive_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.FSharp.Expressions.FSharp_Relational_Expression relational_Expression;
		public FSharp_Relational_Expression relational_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.FSharp.Expressions.FSharp_Logical_And_Expression and_Expression;
		public FSharp_Logical_And_Expression and_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.FSharp.Expressions.FSharp_Logical_Or_Expression or_Expression;
		public FSharp_Logical_Or_Expression or_Expression;
	}

}
