// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import com.eagle.programmar.Haskell.Expressions.Haskell_AdditiveExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_BracketsExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_BuiltInExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_ConcatExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_LogicalAndExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_LogicalNotExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_LogicalOrExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_MultiplicativeExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_NegativeExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_ParenthesizedExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_RangeExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_RelationalExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_SubscriptExpression;
import com.eagle.programmar.Haskell.Expressions.Haskell_VariableExpression;
import com.eagle.programmar.Haskell.Functions.Haskell_DropFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_IfFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_IsPrefixOfFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_LengthFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_MapFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_ModFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_SecondFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_ShowFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_SumFunction;
import com.eagle.programmar.Haskell.Functions.Haskell_UnlinesFunction;
import com.eagle.programmar.Haskell.Symbols.Haskell_Identifier_Reference;
import com.eagle.programmar.Haskell.Terminals.Haskell_Literal;
import com.eagle.programmar.Haskell.Terminals.Haskell_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Haskell_Expression extends PrecedenceChooser implements AbstractExpression
{
	protected static OperatorList _operators = new OperatorList();

	public Haskell_Expression()
	{
		super(_operators);
	}

	public Haskell_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Haskell_Number number;
	public @P(20) Haskell_Literal literal;
	public @P(30) Haskell_Identifier_Reference id;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions

	public @P(100) Haskell_NegativeExpression negativeExpression;
	public @P(110) Haskell_LogicalNotExpression notExpression;
	public @P(120) Haskell_BuiltInExpression builtinExpression;
	public @P(130) Haskell_LengthFunction lengthFunction;
	public @P(140) Haskell_ModFunction modFunction;
	public @P(150) Haskell_SumFunction sumFunction;
	public @P(160) Haskell_ShowFunction showFunction;
	public @P(170) Haskell_SecondFunction secondFunction;
	public @P(180) Haskell_DropFunction dropFunction;
	public @P(190) Haskell_MapFunction mapFunction;
	public @P(200) Haskell_UnlinesFunction unlinesFunction;
	public @P(210) Haskell_IsPrefixOfFunction isPrefixOfFunction;
	public @P(220) Haskell_VariableExpression variableExpression;
	public @P(230) Haskell_ParenthesizedExpression parenExpression;
	public @P(240) Haskell_RangeExpression rangeExpression;
	public @P(250) Haskell_BracketsExpression bracketsExpression;
	public @P(260) Haskell_IfFunction ifFunction;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(1000) Haskell_SubscriptExpression subscriptExpression;
	public @P(1010) Haskell_MultiplicativeExpression multiplicativeExpression;
	public @P(1020) Haskell_AdditiveExpression additiveExpression;
	public @P(1030) Haskell_ConcatExpression concatExpression;
	public @P(1040) Haskell_RelationalExpression relationalExpression;
	public @P(1050) Haskell_LogicalAndExpression andExpression;
	public @P(1060) Haskell_LogicalOrExpression orExpression;
}
