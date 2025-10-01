// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran;

import com.eagle.programmar.Fortran.Expressions.Fortran_AdditiveExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_BracketExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_BuiltIn;
import com.eagle.programmar.Fortran.Expressions.Fortran_EqualityExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_FunctionCall;
import com.eagle.programmar.Fortran.Expressions.Fortran_LogicalAndExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_LogicalNotExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_LogicalOrExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_MultiplicativeExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_NegativeExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_ParenthesizedExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_RelationalExpression;
import com.eagle.programmar.Fortran.Expressions.Fortran_StringConcatenation;
import com.eagle.programmar.Fortran.Expressions.Fortran_Subscript;
import com.eagle.programmar.Fortran.Expressions.Fortran_VariableExpression;
import com.eagle.programmar.Fortran.Functions.Fortran_AdjustLFunction;
import com.eagle.programmar.Fortran.Functions.Fortran_LenFunction;
import com.eagle.programmar.Fortran.Functions.Fortran_ModFunction;
import com.eagle.programmar.Fortran.Functions.Fortran_TrimFunction;
import com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
import com.eagle.programmar.Fortran.Terminals.Fortran_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Fortran_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Fortran_Expression()
	{
		super(_operators);
	}

	public Fortran_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Fortran_Number number;
	public @P(20) Fortran_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Fortran_NegativeExpression negativeExpression;
	public @P(110) Fortran_AdjustLFunction adjustLFunction;
	public @P(120) Fortran_LenFunction lenFunction;
	public @P(130) Fortran_ModFunction modFunction;
	public @P(140) Fortran_TrimFunction trimFunction;
	public @P(150) Fortran_FunctionCall functionCall;
	public @P(160) Fortran_Subscript subscript;
	public @P(170) Fortran_LogicalNotExpression notExpression;
	public @P(180) Fortran_BuiltIn builtIn;
	public @P(190) Fortran_VariableExpression variableExpression;
	public @P(200) Fortran_ParenthesizedExpression parenthesizedExpression;
	public @P(210) Fortran_BracketExpression bracketExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Fortran_MultiplicativeExpression multiplicativeExpression;
	public @P(1010) Fortran_AdditiveExpression additiveExpression;
	public @P(1020) Fortran_StringConcatenation stringConcatenation;
	public @P(1030) Fortran_RelationalExpression relationalExpression;
	public @P(1040) Fortran_EqualityExpression equalityExpression;
	public @P(1050) Fortran_LogicalAndExpression conditionalAndExpression;
	public @P(1060) Fortran_LogicalOrExpression conditionalOrExpression;
}