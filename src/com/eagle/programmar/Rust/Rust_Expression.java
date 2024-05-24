// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rust;

import com.eagle.programmar.Rust.Expressions.Rust_AdditiveExpression;
import com.eagle.programmar.Rust.Expressions.Rust_BuiltIn;
import com.eagle.programmar.Rust.Expressions.Rust_ConditionalAndExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ConditionalOrExpression;
import com.eagle.programmar.Rust.Expressions.Rust_DotDotExpression;
import com.eagle.programmar.Rust.Expressions.Rust_EqualityExpression;
import com.eagle.programmar.Rust.Expressions.Rust_ExpressionArray;
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
import com.eagle.programmar.Rust.Expressions.Rust_VariableExpression;
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
	public @P(120) Rust_NotExpression notExpression;
	public @P(130) Rust_BuiltIn builtIn;
	public @P(140) Rust_VariableExpression variableExpression;
	public @P(150) Rust_RangeExpression rangeExpression;
	public @P(160) Rust_ParenthesizedExpression parenthesizedExpression;
	public @P(170) Rust_ExpressionArray expressionArray;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Rust_SubscriptExpression subscriptExpression;
	public @P(510) Rust_Subfield subfield;
	public @P(520) Rust_MultiplicativeExpression multiplicativeExpression;
	public @P(530) Rust_AdditiveExpression additiveExpression;
	public @P(540) Rust_ShiftExpression shiftExpression;
	public @P(550) Rust_RelationalExpression relationalExpression;
	public @P(560) Rust_EqualityExpression equalityExpression;
	public @P(570) Rust_ConditionalAndExpression conditionalAndExpression;
	public @P(580) Rust_ConditionalOrExpression conditionalOrExpression;
	public @P(590) Rust_DotDotExpression dotDotExpression;
}
