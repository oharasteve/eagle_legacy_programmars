// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Expressions.Python_Additive_Expression;
import com.eagle.programmar.Python.Expressions.Python_Assignment_Expression;
import com.eagle.programmar.Python.Expressions.Python_BackQuotes;
import com.eagle.programmar.Python.Expressions.Python_Bitwise_Expression;
import com.eagle.programmar.Python.Expressions.Python_Bitwise_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_BracesColons;
import com.eagle.programmar.Python.Expressions.Python_BracesNoColons;
import com.eagle.programmar.Python.Expressions.Python_Brackets;
import com.eagle.programmar.Python.Expressions.Python_BuiltIn;
import com.eagle.programmar.Python.Expressions.Python_For_In_Expression;
import com.eagle.programmar.Python.Expressions.Python_Function_Call;
import com.eagle.programmar.Python.Expressions.Python_FunnyConstructor;
import com.eagle.programmar.Python.Expressions.Python_If_Else_Expression;
import com.eagle.programmar.Python.Expressions.Python_If_Expression;
import com.eagle.programmar.Python.Expressions.Python_Lambda_Expression;
import com.eagle.programmar.Python.Expressions.Python_Literals;
import com.eagle.programmar.Python.Expressions.Python_Logical_And_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Not_Expression;
import com.eagle.programmar.Python.Expressions.Python_Logical_Or_Expression;
import com.eagle.programmar.Python.Expressions.Python_Multiplicative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Negative_Expression;
import com.eagle.programmar.Python.Expressions.Python_Parenthesized_Expression;
import com.eagle.programmar.Python.Expressions.Python_Power_Expression;
import com.eagle.programmar.Python.Expressions.Python_RangeExpression;
import com.eagle.programmar.Python.Expressions.Python_Relational_Expression;
import com.eagle.programmar.Python.Expressions.Python_Shift_Expression;
import com.eagle.programmar.Python.Expressions.Python_StarStar_Expression;
import com.eagle.programmar.Python.Expressions.Python_Star_Expression;
import com.eagle.programmar.Python.Expressions.Python_SubfieldExpression;
import com.eagle.programmar.Python.Expressions.Python_SubscriptExpression;
import com.eagle.programmar.Python.Expressions.Python_VariableExpression;
import com.eagle.programmar.Python.Expressions.Python_Yield;
import com.eagle.programmar.Python.Functions.Python_Abs_Function;
import com.eagle.programmar.Python.Functions.Python_Int_Function;
import com.eagle.programmar.Python.Functions.Python_Len_Function;
import com.eagle.programmar.Python.Functions.Python_Locals_Function;
import com.eagle.programmar.Python.Functions.Python_Print_Function;
import com.eagle.programmar.Python.Functions.Python_Str_Function;
import com.eagle.programmar.Python.Methods.Python_EndsWith_Method;
import com.eagle.programmar.Python.Methods.Python_Find_Method;
import com.eagle.programmar.Python.Methods.Python_StartsWith_Method;
import com.eagle.programmar.Python.Methods.Python_Strip_Method;
import com.eagle.programmar.Python.Methods.Python_Upper_Method;
import com.eagle.programmar.Python.Terminals.Python_BinaryNumber;
import com.eagle.programmar.Python.Terminals.Python_HexNumber;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.programmar.Python.Terminals.Python_OctalNumber;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Python_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Python_Expression()
	{
		super(_operators);
	}

	public Python_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Python_BinaryNumber binary;
	public @P(20) Python_OctalNumber octal;
	public @P(30) Python_HexNumber hex;
	public @P(40) Python_Number number;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions

	public @P(100) Python_RangeExpression rangeExpression;
	public @P(110) Python_FunnyConstructor funnyConstructor;
	public @P(120) Python_Parenthesized_Expression parens;
	public @P(130) Python_BracesColons bracesColons;
	public @P(140) Python_BracesNoColons bracesNoColons;
	public @P(150) Python_Brackets brackets;
	public @P(160) Python_Negative_Expression unarySign;
	public @P(170) Python_Logical_Not_Expression logicalNotExpression;
	public @P(180) Python_Bitwise_Not_Expression bitwiseNotExpression;
	public @P(190) Python_Literals literals;
	public @P(200) Python_BackQuotes backQuotes;
	public @P(210) Python_Len_Function lenFunction;
	public @P(220) Python_Str_Function strFunction;
	public @P(230) Python_Int_Function intFunction;
	public @P(240) Python_Abs_Function absFunction;
	public @P(250) Python_Find_Method findFunction;
	public @P(260) Python_StartsWith_Method startsWithFunction;
	public @P(270) Python_EndsWith_Method endsWithFunction;
	public @P(280) Python_Locals_Function localsFunction;
	public @P(290) Python_Print_Function printFunction;
	public @P(300) Python_Function_Call functionCall;
	public @P(310) Python_BuiltIn builtIn;
	public @P(320) Python_VariableExpression variableExpression;
	public @P(330) Python_Star_Expression starExpression;
	public @P(340) Python_StarStar_Expression starStarExpression;
	public @P(350) Python_Lambda_Expression lambdaExpression;
	public @P(360) Python_Yield yield;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(1000) Python_SubscriptExpression subscriptExpression;
	public @P(1010) Python_Upper_Method upperMethod;
	public @P(1020) Python_Strip_Method stripMethod;
	public @P(1030) Python_SubfieldExpression subfield;
	public @P(1040) Python_Power_Expression powerExpression;
	public @P(1050) Python_Multiplicative_Expression multiplicativeExpression;
	public @P(1060) Python_Additive_Expression additiveExpression;
	public @P(1070) Python_Shift_Expression shiftExpression;
	public @P(1080) Python_Bitwise_Expression bitwiseAndExpression;
	public @P(1090) Python_Relational_Expression relationalExpression;
	public @P(1100) Python_Logical_And_Expression logicalAndExpression;
	public @P(1110) Python_Logical_Or_Expression logicalOrExpression;
	public @P(1120) Python_For_In_Expression forInExpression;
	public @P(1130) Python_If_Else_Expression ifElseExpression;
	public @P(1140) Python_If_Expression ifExpression;
	public @P(1150) Python_Assignment_Expression assignmentExpression;
}
