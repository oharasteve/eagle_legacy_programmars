// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BorrowExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
import com.eagle.programmar.Rust.Expressions.Rust_BuiltinMethod;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalAndExpression;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalOrExpression;
import com.eagle.programmar.Rust.Expressions.Rust_DotDotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_EqualityExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray;
import com.eagle.programmar.Rust.Expressions.Rust_MultiplicativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_NegativeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_LogicalNotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ParenthesizedExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RangeExpression;
import com.eagle.programmar.Rust.Expressions.Rust_RelationalExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ShiftExpression;
import com.eagle.programmar.Rust.Expressions.Rust_Subfield;
import com.eagle.programmar.Rust.Expressions.Rust_SubscriptExpression;
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
import com.eagle.programmar.Rust.Functions.Rust_FormatFunction;
import com.eagle.programmar.Rust.Functions.Rust_MethodInvocation;
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

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Rust_MethodInvocation methodInvocation;
	public @P(110) Rust_NegativeExpression negativeExpression;
	public @P(120) Rust_LogicalNotExpression notExpression;
	public @P(130) Rust_BuiltIn builtIn;
	public @P(140) Rust_VariableExpression variableExpression;
	public @P(150) Rust_RangeExpression rangeExpression;
	public @P(160) Rust_FormatFunction builtinFunction;
	public @P(170) Rust_ParenthesizedExpression parenthesizedExpression;
	public @P(180) Rust_ExpressionArray expressionArray;
	public @P(190) Rust_BorrowExpression borrowExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Rust_SubscriptExpression subscriptExpression;
	public @P(1010) Rust_BuiltinMethod builtinMethod;
	public @P(1020) Rust_Subfield subfield;
	public @P(1030) Rust_MultiplicativeExpression multiplicativeExpression;
	public @P(1040) Rust_AdditiveExpression additiveExpression;
	public @P(1050) Rust_ShiftExpression shiftExpression;
	public @P(1060) Rust_RelationalExpression relationalExpression;
	public @P(1070) Rust_EqualityExpression equalityExpression;
	public @P(1080) Rust_LogicalAndExpression conditionalAndExpression;
	public @P(1090) Rust_LogicalOrExpression conditionalOrExpression;
	public @P(1100) Rust_DotDotExpression dotDotExpression;
}
