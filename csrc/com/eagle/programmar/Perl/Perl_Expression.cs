// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

namespace com.eagle.programmar.Perl
{
	using Perl_AdditiveExpression = com.eagle.programmar.Perl.Expressions.Perl_AdditiveExpression;
	using Perl_AddressOfExpression = com.eagle.programmar.Perl.Expressions.Perl_AddressOfExpression;
	using Perl_ArrowExpression = com.eagle.programmar.Perl.Expressions.Perl_ArrowExpression;
	using Perl_AssignmentExpression = com.eagle.programmar.Perl.Expressions.Perl_AssignmentExpression;
	using Perl_BitwiseExpression = com.eagle.programmar.Perl.Expressions.Perl_BitwiseExpression;
	using Perl_BitwiseNotExpression = com.eagle.programmar.Perl.Expressions.Perl_BitwiseNotExpression;
	using Perl_BracesInvocation = com.eagle.programmar.Perl.Expressions.Perl_BracesInvocation;
	using Perl_BracketedExpression = com.eagle.programmar.Perl.Expressions.Perl_BracketedExpression;
	using Perl_BuiltIn = com.eagle.programmar.Perl.Expressions.Perl_BuiltIn;
	using Perl_ClassCastExpression = com.eagle.programmar.Perl.Expressions.Perl_ClassCastExpression;
	using Perl_ClassCreationExpression = com.eagle.programmar.Perl.Expressions.Perl_ClassCreationExpression;
	using Perl_CloneExpression = com.eagle.programmar.Perl.Expressions.Perl_CloneExpression;
	using Perl_ColonColonExpression = com.eagle.programmar.Perl.Expressions.Perl_ColonColonExpression;
	using Perl_DefinedExpression = com.eagle.programmar.Perl.Expressions.Perl_DefinedExpression;
	using Perl_DieExpression = com.eagle.programmar.Perl.Expressions.Perl_DieExpression;
	using Perl_DotExpression = com.eagle.programmar.Perl.Expressions.Perl_DotExpression;
	using Perl_EachExpression = com.eagle.programmar.Perl.Expressions.Perl_EachExpression;
	using Perl_EqualityExpression = com.eagle.programmar.Perl.Expressions.Perl_EqualityExpression;
	using Perl_ExistsExpression = com.eagle.programmar.Perl.Expressions.Perl_ExistsExpression;
	using Perl_Expression_List = com.eagle.programmar.Perl.Expressions.Perl_Expression_List;
	using Perl_FileIO = com.eagle.programmar.Perl.Expressions.Perl_FileIO;
	using Perl_FunctionCall = com.eagle.programmar.Perl.Expressions.Perl_FunctionCall;
	using Perl_FunctionExpression = com.eagle.programmar.Perl.Expressions.Perl_FunctionExpression;
	using Perl_GrepExpression = com.eagle.programmar.Perl.Expressions.Perl_GrepExpression;
	using Perl_InstanceOfExpression = com.eagle.programmar.Perl.Expressions.Perl_InstanceOfExpression;
	using Perl_JoinExpression = com.eagle.programmar.Perl.Expressions.Perl_JoinExpression;
	using Perl_LogicalAndExpression = com.eagle.programmar.Perl.Expressions.Perl_LogicalAndExpression;
	using Perl_LogicalNotExpression = com.eagle.programmar.Perl.Expressions.Perl_LogicalNotExpression;
	using Perl_LogicalOrExpression = com.eagle.programmar.Perl.Expressions.Perl_LogicalOrExpression;
	using Perl_MapExpression = com.eagle.programmar.Perl.Expressions.Perl_MapExpression;
	using Perl_MethodInvocation = com.eagle.programmar.Perl.Expressions.Perl_MethodInvocation;
	using Perl_MultiplicativeExpression = com.eagle.programmar.Perl.Expressions.Perl_MultiplicativeExpression;
	using Perl_NegativeExpression = com.eagle.programmar.Perl.Expressions.Perl_NegativeExpression;
	using Perl_ParenthesizedExpression = com.eagle.programmar.Perl.Expressions.Perl_ParenthesizedExpression;
	using Perl_PercentExpression = com.eagle.programmar.Perl.Expressions.Perl_PercentExpression;
	using Perl_PostIncrementExpression = com.eagle.programmar.Perl.Expressions.Perl_PostIncrementExpression;
	using Perl_PowerExpression = com.eagle.programmar.Perl.Expressions.Perl_PowerExpression;
	using Perl_PreIncrementExpression = com.eagle.programmar.Perl.Expressions.Perl_PreIncrementExpression;
	using Perl_RangeExpression = com.eagle.programmar.Perl.Expressions.Perl_RangeExpression;
	using Perl_ReadExpression = com.eagle.programmar.Perl.Expressions.Perl_ReadExpression;
	using Perl_RegExExpression = com.eagle.programmar.Perl.Expressions.Perl_RegExExpression;
	using Perl_RegExTest = com.eagle.programmar.Perl.Expressions.Perl_RegExTest;
	using Perl_RelationalExpression = com.eagle.programmar.Perl.Expressions.Perl_RelationalExpression;
	using Perl_ShiftExpression = com.eagle.programmar.Perl.Expressions.Perl_ShiftExpression;
	using Perl_StarExpression = com.eagle.programmar.Perl.Expressions.Perl_StarExpression;
	using Perl_SubscriptExpression = com.eagle.programmar.Perl.Expressions.Perl_SubscriptExpression;
	using Perl_TrueFalseExpression = com.eagle.programmar.Perl.Expressions.Perl_TrueFalseExpression;
	using Perl_VariableExpression = com.eagle.programmar.Perl.Expressions.Perl_VariableExpression;
	using Perl_ArrayFunction = com.eagle.programmar.Perl.Functions.Perl_ArrayFunction;
	using Perl_IntValFunction = com.eagle.programmar.Perl.Functions.Perl_IntValFunction;
	using Perl_StrLenFunction = com.eagle.programmar.Perl.Functions.Perl_StrLenFunction;
	using Perl_SubstrFunction = com.eagle.programmar.Perl.Functions.Perl_SubstrFunction;
	using Perl_HexNumber = com.eagle.programmar.Perl.Terminals.Perl_HexNumber;
	using Perl_Literal = com.eagle.programmar.Perl.Terminals.Perl_Literal;
	using Perl_Number = com.eagle.programmar.Perl.Terminals.Perl_Number;
	using Perl_OctalNumber = com.eagle.programmar.Perl.Terminals.Perl_OctalNumber;
	using PrecedenceChooser = com.eagle.tokens.PrecedenceChooser;
	using PrecedenceOperator = com.eagle.tokens.PrecedenceOperator;
	using AllowedPrecedence = com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

	public class Perl_Expression : PrecedenceChooser
	{
		private static OperatorList _operators = new OperatorList();

		public Perl_Expression() : base(_operators)
		{
		}

		public Perl_Expression(PrecedenceOperator token, PrecedenceOperator.AllowedPrecedence allowed) : base(_operators, allowed, token.GetType())
		{
		}

		//
		// Note: All fields should stay in @P(#) order. The # determines operator
		// precedence.
		//

		///////////////////////////////////////////////
		// Terminals

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(10) com.eagle.programmar.Perl.Terminals.Perl_OctalNumber octal;
		public Perl_OctalNumber octal;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(20) com.eagle.programmar.Perl.Terminals.Perl_HexNumber hex;
		public Perl_HexNumber hex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(30) com.eagle.programmar.Perl.Terminals.Perl_Number number;
		public Perl_Number number;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(40) com.eagle.programmar.Perl.Terminals.Perl_Literal literal;
		public Perl_Literal literal;

		///////////////////////////////////////////////
		// Primary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(100) com.eagle.programmar.Perl.Expressions.Perl_ClassCastExpression classCastExpression;
		public Perl_ClassCastExpression classCastExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(110) com.eagle.programmar.Perl.Expressions.Perl_Expression_List expression_List;
		public Perl_Expression_List expression_List;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(120) com.eagle.programmar.Perl.Expressions.Perl_ClassCreationExpression classCreationExpression;
		public Perl_ClassCreationExpression classCreationExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(130) com.eagle.programmar.Perl.Expressions.Perl_CloneExpression cloneExpression;
		public Perl_CloneExpression cloneExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(140) com.eagle.programmar.Perl.Expressions.Perl_DefinedExpression definedExpression;
		public Perl_DefinedExpression definedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(150) com.eagle.programmar.Perl.Expressions.Perl_MethodInvocation methodInvocation;
		public Perl_MethodInvocation methodInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(160) com.eagle.programmar.Perl.Expressions.Perl_BracesInvocation bracesInvocation;
		public Perl_BracesInvocation bracesInvocation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(170) com.eagle.programmar.Perl.Expressions.Perl_PreIncrementExpression preIncrementExpression;
		public Perl_PreIncrementExpression preIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(180) com.eagle.programmar.Perl.Expressions.Perl_PostIncrementExpression postIncrementExpression;
		public Perl_PostIncrementExpression postIncrementExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(190) com.eagle.programmar.Perl.Expressions.Perl_ExistsExpression existsExpression;
		public Perl_ExistsExpression existsExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(200) com.eagle.programmar.Perl.Expressions.Perl_NegativeExpression negativeExpression;
		public Perl_NegativeExpression negativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(210) com.eagle.programmar.Perl.Expressions.Perl_BitwiseNotExpression logicalNotExpression;
		public Perl_BitwiseNotExpression logicalNotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(220) com.eagle.programmar.Perl.Expressions.Perl_LogicalNotExpression notExpression;
		public Perl_LogicalNotExpression notExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(230) com.eagle.programmar.Perl.Expressions.Perl_StarExpression starExpression;
		public Perl_StarExpression starExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(240) com.eagle.programmar.Perl.Expressions.Perl_GrepExpression grepExpression;
		public Perl_GrepExpression grepExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(250) com.eagle.programmar.Perl.Expressions.Perl_ReadExpression readExpression;
		public Perl_ReadExpression readExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(260) com.eagle.programmar.Perl.Expressions.Perl_BuiltIn builtIn;
		public Perl_BuiltIn builtIn;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(270) com.eagle.programmar.Perl.Expressions.Perl_RegExExpression regExExpression;
		public Perl_RegExExpression regExExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(280) com.eagle.programmar.Perl.Functions.Perl_ArrayFunction arrayFunction;
		public Perl_ArrayFunction arrayFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(290) com.eagle.programmar.Perl.Functions.Perl_IntValFunction intFunction;
		public Perl_IntValFunction intFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(300) com.eagle.programmar.Perl.Functions.Perl_StrLenFunction strlenFunction;
		public Perl_StrLenFunction strlenFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(310) com.eagle.programmar.Perl.Functions.Perl_SubstrFunction substrFunction;
		public Perl_SubstrFunction substrFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(320) com.eagle.programmar.Perl.Expressions.Perl_FunctionCall functionCall;
		public Perl_FunctionCall functionCall;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(330) com.eagle.programmar.Perl.Expressions.Perl_VariableExpression variableExpression;
		public Perl_VariableExpression variableExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(340) com.eagle.programmar.Perl.Expressions.Perl_ParenthesizedExpression parenthesizedExpression;
		public Perl_ParenthesizedExpression parenthesizedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(350) com.eagle.programmar.Perl.Expressions.Perl_BracketedExpression bracketedExpression;
		public Perl_BracketedExpression bracketedExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(360) com.eagle.programmar.Perl.Expressions.Perl_EachExpression eachExpression;
		public Perl_EachExpression eachExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(370) com.eagle.programmar.Perl.Expressions.Perl_DieExpression dieExpression;
		public Perl_DieExpression dieExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(380) com.eagle.programmar.Perl.Expressions.Perl_AddressOfExpression addressOfExpression;
		public Perl_AddressOfExpression addressOfExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(390) com.eagle.programmar.Perl.Expressions.Perl_FunctionExpression functionExpression;
		public Perl_FunctionExpression functionExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(400) com.eagle.programmar.Perl.Expressions.Perl_FileIO fileIO;
		public Perl_FileIO fileIO;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(410) com.eagle.programmar.Perl.Expressions.Perl_PercentExpression percentExpression;
		public Perl_PercentExpression percentExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(420) com.eagle.programmar.Perl.Expressions.Perl_JoinExpression joinExpression;
		public Perl_JoinExpression joinExpression;

		///////////////////////////////////////////////
		// Binary expressions

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1000) com.eagle.programmar.Perl.Expressions.Perl_SubscriptExpression subscriptExpression;
		public Perl_SubscriptExpression subscriptExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1010) com.eagle.programmar.Perl.Expressions.Perl_DotExpression dotExpression;
		public Perl_DotExpression dotExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1020) com.eagle.programmar.Perl.Expressions.Perl_ColonColonExpression colonColonExpression;
		public Perl_ColonColonExpression colonColonExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1030) com.eagle.programmar.Perl.Expressions.Perl_ArrowExpression arrowExpression;
		public Perl_ArrowExpression arrowExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1040) com.eagle.programmar.Perl.Expressions.Perl_MapExpression mapExpression;
		public Perl_MapExpression mapExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1050) com.eagle.programmar.Perl.Expressions.Perl_PowerExpression powerExpression;
		public Perl_PowerExpression powerExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1060) com.eagle.programmar.Perl.Expressions.Perl_MultiplicativeExpression multiplicativeExpression;
		public Perl_MultiplicativeExpression multiplicativeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1070) com.eagle.programmar.Perl.Expressions.Perl_AdditiveExpression additiveExpression;
		public Perl_AdditiveExpression additiveExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1080) com.eagle.programmar.Perl.Expressions.Perl_ShiftExpression shiftExpression;
		public Perl_ShiftExpression shiftExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1090) com.eagle.programmar.Perl.Expressions.Perl_RelationalExpression relationalExpression;
		public Perl_RelationalExpression relationalExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1100) com.eagle.programmar.Perl.Expressions.Perl_RegExTest regExTest;
		public Perl_RegExTest regExTest;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1110) com.eagle.programmar.Perl.Expressions.Perl_InstanceOfExpression instanceOfExpression;
		public Perl_InstanceOfExpression instanceOfExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1120) com.eagle.programmar.Perl.Expressions.Perl_EqualityExpression equalityExpression;
		public Perl_EqualityExpression equalityExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1130) com.eagle.programmar.Perl.Expressions.Perl_BitwiseExpression bitwiseExpression;
		public Perl_BitwiseExpression bitwiseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1140) com.eagle.programmar.Perl.Expressions.Perl_LogicalAndExpression logicalAndExpression;
		public Perl_LogicalAndExpression logicalAndExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1150) com.eagle.programmar.Perl.Expressions.Perl_LogicalOrExpression logicalOrExpression;
		public Perl_LogicalOrExpression logicalOrExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1160) com.eagle.programmar.Perl.Expressions.Perl_TrueFalseExpression trueFalseExpression;
		public Perl_TrueFalseExpression trueFalseExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1170) com.eagle.programmar.Perl.Expressions.Perl_RangeExpression rangeExpression;
		public Perl_RangeExpression rangeExpression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @P(1180) com.eagle.programmar.Perl.Expressions.Perl_AssignmentExpression assignmentExpression;
		public Perl_AssignmentExpression assignmentExpression;
	}

}
