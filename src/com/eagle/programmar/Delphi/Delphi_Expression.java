// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Expressions.Delphi_Additive_Expression;
import com.eagle.programmar.Delphi.Expressions.Delphi_Brackets;
import com.eagle.programmar.Delphi.Expressions.Delphi_Builtins;
import com.eagle.programmar.Delphi.Expressions.Delphi_Cast;
import com.eagle.programmar.Delphi.Expressions.Delphi_DotDot_Expression;
import com.eagle.programmar.Delphi.Expressions.Delphi_Dot_Expression;
import com.eagle.programmar.Delphi.Expressions.Delphi_Function_Call;
import com.eagle.programmar.Delphi.Expressions.Delphi_Multiplicative_Expression;
import com.eagle.programmar.Delphi.Expressions.Delphi_Not_Operator;
import com.eagle.programmar.Delphi.Expressions.Delphi_Parentheses;
import com.eagle.programmar.Delphi.Expressions.Delphi_Relational_Expression;
import com.eagle.programmar.Delphi.Expressions.Delphi_UnarySign;
import com.eagle.programmar.Delphi.Expressions.Delphi_Variable_Expression;
import com.eagle.programmar.Delphi.Functions.Delphi_Copy_Function;
import com.eagle.programmar.Delphi.Functions.Delphi_Format_Function;
import com.eagle.programmar.Delphi.Functions.Delphi_Length_Function;
import com.eagle.programmar.Delphi.Functions.Delphi_Odd_Function;
import com.eagle.programmar.Delphi.Functions.Delphi_Pred_Function;
import com.eagle.programmar.Delphi.Functions.Delphi_Succ_Function;
import com.eagle.programmar.Delphi.Terminals.Delphi_Character;
import com.eagle.programmar.Delphi.Terminals.Delphi_HexNumber;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.programmar.Delphi.Terminals.Delphi_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Delphi_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Delphi_Expression()
	{
		super(_operators);
	}

	public Delphi_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Delphi_Number number;
	public @P(20) Delphi_HexNumber hex;
	public @P(30) Delphi_Literal literal;
	public @P(40) Delphi_Character character;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions

	public @P(100) Delphi_Builtins builtins;
	public @P(110) Delphi_Parentheses parens;
	public @P(120) Delphi_Brackets brackets;
	public @P(130) Delphi_Copy_Function copyFunction;
	public @P(140) Delphi_Format_Function formatFunction;
	public @P(150) Delphi_Length_Function lengthFunction;
	public @P(160) Delphi_Odd_Function oddFunction;
	public @P(170) Delphi_Pred_Function predFunction;
	public @P(180) Delphi_Succ_Function succFunction;
	public @P(190) Delphi_Function_Call functionCall;
	public @P(200) Delphi_Cast cast;
	public @P(210) Delphi_Variable_Expression variableExpression;
	public @P(220) Delphi_UnarySign unarySign;
	public @P(230) Delphi_Not_Operator notOp;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(1000) Delphi_Dot_Expression dotExpression;
	public @P(1010) Delphi_Multiplicative_Expression multiplicativeExpression;
	public @P(1020) Delphi_Additive_Expression additiveExpression;
	public @P(1030) Delphi_Relational_Expression relationalExpression;
	public @P(1040) Delphi_DotDot_Expression dotDotExpression;
}
