// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell
{
	using Powershell_GetChildItem = com.eagle.programmar.Powershell.Commands.Powershell_GetChildItem;
	using Powershell_GetContent = com.eagle.programmar.Powershell.Commands.Powershell_GetContent;
	using Powershell_TestPath = com.eagle.programmar.Powershell.Commands.Powershell_TestPath;
	using Powershell_Additive_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_Additive_Expression;
	using Powershell_AmpersandOperator = com.eagle.programmar.Powershell.Expressions.Powershell_AmpersandOperator;
	using Powershell_AssignmentExpression = com.eagle.programmar.Powershell.Expressions.Powershell_AssignmentExpression;
	using Powershell_BangOperator = com.eagle.programmar.Powershell.Expressions.Powershell_BangOperator;
	using Powershell_BuiltIn = com.eagle.programmar.Powershell.Expressions.Powershell_BuiltIn;
	using Powershell_BuiltinVariable = com.eagle.programmar.Powershell.Expressions.Powershell_BuiltinVariable;
	using Powershell_Cast = com.eagle.programmar.Powershell.Expressions.Powershell_Cast;
	using Powershell_Dictionary = com.eagle.programmar.Powershell.Expressions.Powershell_Dictionary;
	using Powershell_EvaluateExpression = com.eagle.programmar.Powershell.Expressions.Powershell_EvaluateExpression;
	using Powershell_FunctionCall = com.eagle.programmar.Powershell.Expressions.Powershell_FunctionCall;
	using Powershell_IsExpression = com.eagle.programmar.Powershell.Expressions.Powershell_IsExpression;
	using Powershell_LibraryVariable = com.eagle.programmar.Powershell.Expressions.Powershell_LibraryVariable;
	using Powershell_List = com.eagle.programmar.Powershell.Expressions.Powershell_List;
	using Powershell_LogicalAnd_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_LogicalAnd_Expression;
	using Powershell_LogicalNotOperator = com.eagle.programmar.Powershell.Expressions.Powershell_LogicalNotOperator;
	using Powershell_LogicalOr_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_LogicalOr_Expression;
	using Powershell_Match_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_Match_Expression;
	using Powershell_Multiplicative_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_Multiplicative_Expression;
	using Powershell_Negative = com.eagle.programmar.Powershell.Expressions.Powershell_Negative;
	using Powershell_ParenthesizedExpression = com.eagle.programmar.Powershell.Expressions.Powershell_ParenthesizedExpression;
	using Powershell_PipeExpression = com.eagle.programmar.Powershell.Expressions.Powershell_PipeExpression;
	using Powershell_PostIncrementExpression = com.eagle.programmar.Powershell.Expressions.Powershell_PostIncrementExpression;
	using Powershell_PreIncrementExpression = com.eagle.programmar.Powershell.Expressions.Powershell_PreIncrementExpression;
	using Powershell_RangeExpression = com.eagle.programmar.Powershell.Expressions.Powershell_RangeExpression;
	using Powershell_Relational_Expression = com.eagle.programmar.Powershell.Expressions.Powershell_Relational_Expression;
	using Powershell_SubfieldExpression = com.eagle.programmar.Powershell.Expressions.Powershell_SubfieldExpression;
	using Powershell_SubscriptExpression = com.eagle.programmar.Powershell.Expressions.Powershell_SubscriptExpression;
	using Powershell_VariableExpression = com.eagle.programmar.Powershell.Expressions.Powershell_VariableExpression;
	using Powershell_FloorFunction = com.eagle.programmar.Powershell.Functions.Powershell_FloorFunction;
	using Powershell_TruncateFunction = com.eagle.programmar.Powershell.Functions.Powershell_TruncateFunction;
	using Powershell_LengthMethod = com.eagle.programmar.Powershell.Methods.Powershell_LengthMethod;
	using Powershell_StartsWithMethod = com.eagle.programmar.Powershell.Methods.Powershell_StartsWithMethod;
	using Powershell_SubStringMethod = com.eagle.programmar.Powershell.Methods.Powershell_SubStringMethod;
	using Powershell_Literal = com.eagle.programmar.Powershell.Terminals.Powershell_Literal;
	using Powershell_Number = com.eagle.programmar.Powershell.Terminals.Powershell_Number;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Powershell_Expression : PrecedenceChooser
	{
		protected internal static OperatorList _operators = new OperatorList();

		public Powershell_Expression() : base(_operators)
		{
		}

		public Powershell_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Powershell.Terminals.Powershell_Number number;
		public Powershell_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Powershell.Terminals.Powershell_Literal literal;
		public Powershell_Literal literal;

		///////////////////////////////////////////////////////////////////////////
		// Primary Expressions

		// Powershell Commands
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Powershell.Commands.Powershell_TestPath testPathCommand;
		public Powershell_TestPath testPathCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Powershell.Commands.Powershell_GetChildItem getChildItemCommand;
		public Powershell_GetChildItem getChildItemCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Powershell.Commands.Powershell_GetContent getContentCommand;
		public Powershell_GetContent getContentCommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Powershell.Expressions.Powershell_PreIncrementExpression preIncrementExpression;
		public Powershell_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Powershell.Expressions.Powershell_PostIncrementExpression postIncrementExpression;
		public Powershell_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Powershell.Expressions.Powershell_LogicalNotOperator notOp;
		public Powershell_LogicalNotOperator notOp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Powershell.Expressions.Powershell_Negative negative;
		public Powershell_Negative negative;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Powershell.Expressions.Powershell_BangOperator bangOp;
		public Powershell_BangOperator bangOp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Powershell.Expressions.Powershell_AmpersandOperator ampersandOp;
		public Powershell_AmpersandOperator ampersandOp;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Powershell.Expressions.Powershell_ParenthesizedExpression parenthesizedExpression;
		public Powershell_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Powershell.Expressions.Powershell_List lists;
		public Powershell_List lists;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Powershell.Expressions.Powershell_Dictionary dictionary;
		public Powershell_Dictionary dictionary;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Powershell.Functions.Powershell_FloorFunction floorFunction;
		public Powershell_FloorFunction floorFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Powershell.Functions.Powershell_TruncateFunction truncateFunction;
		public Powershell_TruncateFunction truncateFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.Powershell.Expressions.Powershell_FunctionCall functionCall;
		public Powershell_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.Powershell.Expressions.Powershell_Cast cast;
		public Powershell_Cast cast;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.Powershell.Expressions.Powershell_EvaluateExpression evaluateExpression;
		public Powershell_EvaluateExpression evaluateExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.Powershell.Expressions.Powershell_BuiltIn builtIn;
		public Powershell_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.Powershell.Expressions.Powershell_BuiltinVariable builtinVariable;
		public Powershell_BuiltinVariable builtinVariable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.Powershell.Expressions.Powershell_VariableExpression variableExpression;
		public Powershell_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.Powershell.Expressions.Powershell_LibraryVariable libraryVariable;
		public Powershell_LibraryVariable libraryVariable;

		///////////////////////////////////////////////////////////////////////////
		// Binary Expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Powershell.Methods.Powershell_LengthMethod subfieldExpression;
		public Powershell_LengthMethod subfieldExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Powershell.Methods.Powershell_StartsWithMethod startsWithExpression;
		public Powershell_StartsWithMethod startsWithExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Powershell.Methods.Powershell_SubStringMethod subStringExpression;
		public Powershell_SubStringMethod subStringExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Powershell.Expressions.Powershell_SubscriptExpression subscriptExpression;
		public Powershell_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Powershell.Expressions.Powershell_SubfieldExpression subfield;
		public Powershell_SubfieldExpression subfield;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Powershell.Expressions.Powershell_Multiplicative_Expression multiplicative_Expression;
		public Powershell_Multiplicative_Expression multiplicative_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Powershell.Expressions.Powershell_Additive_Expression additive_Expression;
		public Powershell_Additive_Expression additive_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Powershell.Expressions.Powershell_Relational_Expression relational_Expression;
		public Powershell_Relational_Expression relational_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Powershell.Expressions.Powershell_LogicalAnd_Expression logicalAnd_Expression;
		public Powershell_LogicalAnd_Expression logicalAnd_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Powershell.Expressions.Powershell_LogicalOr_Expression logicalOr_Expression;
		public Powershell_LogicalOr_Expression logicalOr_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Powershell.Expressions.Powershell_Match_Expression match_Expression;
		public Powershell_Match_Expression match_Expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Powershell.Expressions.Powershell_IsExpression isExpression;
		public Powershell_IsExpression isExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Powershell.Expressions.Powershell_AssignmentExpression assignmentExpression;
		public Powershell_AssignmentExpression assignmentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.Powershell.Expressions.Powershell_PipeExpression pipeExpression;
		public Powershell_PipeExpression pipeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.Powershell.Expressions.Powershell_RangeExpression rangeExpression;
		public Powershell_RangeExpression rangeExpression;
	}

}
