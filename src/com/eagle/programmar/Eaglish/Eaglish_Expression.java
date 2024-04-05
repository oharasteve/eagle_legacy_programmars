// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.programmar.Eaglish.Expressions.Eaglish_AdditiveExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_BuiltInExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_BuiltinFunction;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_ConditionStringMatch;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_ConditionalAndExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_ConditionalOrExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_FunctionCall;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_MultiplicativeExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_NegativeExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_ParenthesizedExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_RelationalExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_SubscriptExpression;
import com.eagle.programmar.Eaglish.Expressions.Eaglish_VariableExpression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Literal;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Eaglish_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Eaglish_Expression()
	{
	    super(_operators);
	}

	public Eaglish_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Eaglish_Number number;
	public @P(20) Eaglish_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Eaglish_BuiltinFunction builtinFunction;
	public @P(110) Eaglish_FunctionCall funcCall;
	public @P(120) Eaglish_NegativeExpression negativeExpr;
	public @P(130) Eaglish_ParenthesizedExpression parenExpr;
	public @P(140) Eaglish_BuiltInExpression builtinExpr;
	public @P(150) Eaglish_VariableExpression varExpr;
	
	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Eaglish_SubscriptExpression subscrExpr;
	public @P(510) Eaglish_MultiplicativeExpression multExpr;
	public @P(520) Eaglish_AdditiveExpression addExpr;
	public @P(530) Eaglish_RelationalExpression relExpr;
	public @P(540) Eaglish_ConditionStringMatch matchExpr;
	public @P(550) Eaglish_ConditionalAndExpression andExpr;
	public @P(560) Eaglish_ConditionalOrExpression orExpr;
}