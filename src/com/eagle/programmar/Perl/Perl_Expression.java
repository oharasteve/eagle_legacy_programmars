// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Expressions.Perl_AdditiveExpression;
import com.eagle.programmar.Perl.Expressions.Perl_AddressOfExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ArrowExpression;
import com.eagle.programmar.Perl.Expressions.Perl_AssignmentExpression;
import com.eagle.programmar.Perl.Expressions.Perl_BitwiseExpression;
import com.eagle.programmar.Perl.Expressions.Perl_BitwiseNotExpression;
import com.eagle.programmar.Perl.Expressions.Perl_BracesInvocation;
import com.eagle.programmar.Perl.Expressions.Perl_BracketedExpression;
import com.eagle.programmar.Perl.Expressions.Perl_BuiltIn;
import com.eagle.programmar.Perl.Expressions.Perl_ClassCastExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ClassCreationExpression;
import com.eagle.programmar.Perl.Expressions.Perl_CloneExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ColonColonExpression;
import com.eagle.programmar.Perl.Expressions.Perl_DefinedExpression;
import com.eagle.programmar.Perl.Expressions.Perl_DieExpression;
import com.eagle.programmar.Perl.Expressions.Perl_DotExpression;
import com.eagle.programmar.Perl.Expressions.Perl_EachExpression;
import com.eagle.programmar.Perl.Expressions.Perl_EqualityExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ExistsExpression;
import com.eagle.programmar.Perl.Expressions.Perl_Expression_List;
import com.eagle.programmar.Perl.Expressions.Perl_FileIO;
import com.eagle.programmar.Perl.Expressions.Perl_FunctionCall;
import com.eagle.programmar.Perl.Expressions.Perl_FunctionExpression;
import com.eagle.programmar.Perl.Expressions.Perl_GrepExpression;
import com.eagle.programmar.Perl.Expressions.Perl_InstanceOfExpression;
import com.eagle.programmar.Perl.Expressions.Perl_JoinExpression;
import com.eagle.programmar.Perl.Expressions.Perl_LogicalAndExpression;
import com.eagle.programmar.Perl.Expressions.Perl_LogicalNotExpression;
import com.eagle.programmar.Perl.Expressions.Perl_LogicalOrExpression;
import com.eagle.programmar.Perl.Expressions.Perl_MapExpression;
import com.eagle.programmar.Perl.Expressions.Perl_MethodInvocation;
import com.eagle.programmar.Perl.Expressions.Perl_MultiplicativeExpression;
import com.eagle.programmar.Perl.Expressions.Perl_NegativeExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ParenthesizedExpression;
import com.eagle.programmar.Perl.Expressions.Perl_PercentExpression;
import com.eagle.programmar.Perl.Expressions.Perl_PostIncrementExpression;
import com.eagle.programmar.Perl.Expressions.Perl_PowerExpression;
import com.eagle.programmar.Perl.Expressions.Perl_PreIncrementExpression;
import com.eagle.programmar.Perl.Expressions.Perl_RangeExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ReadExpression;
import com.eagle.programmar.Perl.Expressions.Perl_RegExExpression;
import com.eagle.programmar.Perl.Expressions.Perl_RegExTest;
import com.eagle.programmar.Perl.Expressions.Perl_RelationalExpression;
import com.eagle.programmar.Perl.Expressions.Perl_ShiftExpression;
import com.eagle.programmar.Perl.Expressions.Perl_StarExpression;
import com.eagle.programmar.Perl.Expressions.Perl_SubscriptExpression;
import com.eagle.programmar.Perl.Expressions.Perl_TrueFalseExpression;
import com.eagle.programmar.Perl.Expressions.Perl_VariableExpression;
import com.eagle.programmar.Perl.Functions.Perl_ArrayFunction;
import com.eagle.programmar.Perl.Functions.Perl_FloatValFunction;
import com.eagle.programmar.Perl.Functions.Perl_IntValFunction;
import com.eagle.programmar.Perl.Functions.Perl_StrLenFunction;
import com.eagle.programmar.Perl.Functions.Perl_SubstrFunction;
import com.eagle.programmar.Perl.Terminals.Perl_HexNumber;
import com.eagle.programmar.Perl.Terminals.Perl_Literal;
import com.eagle.programmar.Perl.Terminals.Perl_Number;
import com.eagle.programmar.Perl.Terminals.Perl_OctalNumber;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Perl_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Perl_Expression()
	{
		super(_operators);
	}

	public Perl_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Perl_OctalNumber octal;
	public @P(20) Perl_HexNumber hex;
	public @P(30) Perl_Number number;
	public @P(40) Perl_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Perl_ClassCastExpression classCastExpression;
	public @P(110) Perl_Expression_List expression_List;
	public @P(120) Perl_ClassCreationExpression classCreationExpression;
	public @P(130) Perl_CloneExpression cloneExpression;
	public @P(140) Perl_DefinedExpression definedExpression;
	public @P(150) Perl_MethodInvocation methodInvocation;
	public @P(160) Perl_BracesInvocation bracesInvocation;
	public @P(170) Perl_PreIncrementExpression preIncrementExpression;
	public @P(180) Perl_PostIncrementExpression postIncrementExpression;
	public @P(190) Perl_ExistsExpression existsExpression;
	public @P(200) Perl_NegativeExpression negativeExpression;
	public @P(210) Perl_BitwiseNotExpression logicalNotExpression;
	public @P(220) Perl_LogicalNotExpression notExpression;
	public @P(230) Perl_StarExpression starExpression;
	public @P(240) Perl_GrepExpression grepExpression;
	public @P(250) Perl_ReadExpression readExpression;
	public @P(260) Perl_BuiltIn builtIn;
	public @P(270) Perl_RegExExpression regExExpression;
	public @P(280) Perl_ArrayFunction arrayFunction;
	public @P(290) Perl_IntValFunction intFunction;
	public @P(300) Perl_FloatValFunction floatFunction;
	public @P(310) Perl_StrLenFunction strlenFunction;
	public @P(320) Perl_SubstrFunction substrFunction;
	public @P(330) Perl_FunctionCall functionCall;
	public @P(340) Perl_VariableExpression variableExpression;
	public @P(350) Perl_ParenthesizedExpression parenthesizedExpression;
	public @P(360) Perl_BracketedExpression bracketedExpression;
	public @P(370) Perl_EachExpression eachExpression;
	public @P(380) Perl_DieExpression dieExpression;
	public @P(390) Perl_AddressOfExpression addressOfExpression;
	public @P(400) Perl_FunctionExpression functionExpression;
	public @P(410) Perl_FileIO fileIO;
	public @P(420) Perl_PercentExpression percentExpression;
	public @P(430) Perl_JoinExpression joinExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Perl_SubscriptExpression subscriptExpression;
	public @P(1010) Perl_DotExpression dotExpression;
	public @P(1020) Perl_ColonColonExpression colonColonExpression;
	public @P(1030) Perl_ArrowExpression arrowExpression;
	public @P(1040) Perl_MapExpression mapExpression;
	public @P(1050) Perl_PowerExpression powerExpression;
	public @P(1060) Perl_MultiplicativeExpression multiplicativeExpression;
	public @P(1070) Perl_AdditiveExpression additiveExpression;
	public @P(1080) Perl_ShiftExpression shiftExpression;
	public @P(1090) Perl_RelationalExpression relationalExpression;
	public @P(1100) Perl_RegExTest regExTest;
	public @P(1110) Perl_InstanceOfExpression instanceOfExpression;
	public @P(1120) Perl_EqualityExpression equalityExpression;
	public @P(1130) Perl_BitwiseExpression bitwiseExpression;
	public @P(1140) Perl_LogicalAndExpression logicalAndExpression;
	public @P(1150) Perl_LogicalOrExpression logicalOrExpression;
	public @P(1160) Perl_TrueFalseExpression trueFalseExpression;
	public @P(1170) Perl_RangeExpression rangeExpression;
	public @P(1180) Perl_AssignmentExpression assignmentExpression;
}
