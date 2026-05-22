// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_AsExpression;
import com.eagle.programmar.Rust.Expressions.Rust_AssignmentExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BitwiseExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BorrowExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
import com.eagle.programmar.Rust.Expressions.Rust_CastExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ClassCreationExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression;
import com.eagle.programmar.Rust.Expressions.Rust_MethodInvocation;
import com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RangeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression;
import com.eagle.programmar.Rust.Expressions.Rust_Subfield;
import com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
import com.eagle.programmar.Rust.Expressions.Rust_TypeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_Underscore;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Functions.Rust_AbsMethod;
import com.eagle.programmar.Rust.Functions.Rust_FindMethod;
import com.eagle.programmar.Rust.Functions.Rust_FormatFunction;
import com.eagle.programmar.Rust.Functions.Rust_LenMethod;
import com.eagle.programmar.Rust.Functions.Rust_MapMethod;
import com.eagle.programmar.Rust.Functions.Rust_ModMethod;
import com.eagle.programmar.Rust.Functions.Rust_PowMethod;
import com.eagle.programmar.Rust.Functions.Rust_PrintlnFunction;
import com.eagle.programmar.Rust.Functions.Rust_RevMethod;
import com.eagle.programmar.Rust.Functions.Rust_StartsWithMethod;
import com.eagle.programmar.Rust.Functions.Rust_StepByMethod;
import com.eagle.programmar.Rust.Functions.Rust_ToOwnedMethod;
import com.eagle.programmar.Rust.Functions.Rust_ToStringMethod;
import com.eagle.programmar.Rust.Functions.Rust_TrimMethod;
import com.eagle.programmar.Rust.Functions.Rust_UnwrapMethod;
import com.eagle.programmar.Rust.Terminals.Rust_BinaryNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Character_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_HexNumber;
import com.eagle.programmar.Rust.Terminals.Rust_Literal;
import com.eagle.programmar.Rust.Terminals.Rust_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Rust_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Rust_Expression()
	{
		super(_operators);
	}

	public Rust_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Rust_BinaryNumber bin;
	public @P(20) Rust_HexNumber hex;
	public @P(30) Rust_Number number;
	public @P(40) Rust_Literal literal;
	public @P(50) Rust_Character_Literal characters;
	public @P(60) Rust_Underscore underscore;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Rust_MethodInvocation methodInvocation;
	public @P(110) Rust_NegativeExpression negativeExpression;
	public @P(120) Rust_AbsMethod absMethod;
	public @P(130) Rust_ModMethod modMethod;
	public @P(140) Rust_NotExpression notExpression;
	public @P(150) Rust_BuiltIn builtIn;
	public @P(160) Rust_VariableExpression variableExpression;
	public @P(170) Rust_CastExpression castExpression;
	public @P(180) Rust_FormatFunction builtinFunction;
	public @P(190) Rust_ParenthesizedExpression parenthesizedExpression;
	public @P(200) Rust_ExpressionArray expressionArray;
	public @P(210) Rust_BorrowExpression borrowExpression;
	public @P(220) Rust_PrintlnFunction printlnStatement;
	public @P(230) Rust_TypeExpression typeExpression;
	public @P(240) Rust_ClassCreationExpression createExpression;
	
	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Rust_SubscriptExpression subscriptExpression;
	public @P(1010) Rust_LenMethod lenMethod;
	public @P(1020) Rust_PowMethod powMethod;
	public @P(1030) Rust_RevMethod revMethod;
	public @P(1040) Rust_StepByMethod stepByMethod;
	public @P(1050) Rust_StartsWithMethod startsWithMethod;
	public @P(1060) Rust_ToStringMethod toStringMethod;
	public @P(1070) Rust_ToOwnedMethod toOwnedMethod;
	public @P(1080) Rust_TrimMethod trimMethod;
	public @P(1090) Rust_FindMethod findMethod;
	public @P(1100) Rust_MapMethod mapMethod;
	public @P(1110) Rust_UnwrapMethod unwrapMethod;
	public @P(1120) Rust_Subfield subfield;
	public @P(1130) Rust_MultiplicativeExpression multiplicativeExpression;
	public @P(1140) Rust_AdditiveExpression additiveExpression;
	public @P(1150) Rust_ShiftExpression shiftExpression;
	public @P(1160) Rust_RelationalExpression relationalExpression;
	public @P(1170) Rust_BitwiseExpression bitwiseExpression;
	public @P(1180) Rust_LogicalAndExpression conditionalAndExpression;
	public @P(1190) Rust_LogicalOrExpression conditionalOrExpression;
	public @P(1200) Rust_RangeExpression rangeExpression;
	public @P(1210) Rust_AsExpression asExpression;
	public @P(1220) Rust_AssignmentExpression asgExpression;
}
