// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go;

import com.eagle.programmar.Go.Expressions.Go_AdditiveExpression;
import com.eagle.programmar.Go.Expressions.Go_AmpersandExpression;
import com.eagle.programmar.Go.Expressions.Go_AssignmentExpression;
import com.eagle.programmar.Go.Expressions.Go_BracesExpression;
import com.eagle.programmar.Go.Expressions.Go_BuiltIn;
import com.eagle.programmar.Go.Expressions.Go_Constructor;
import com.eagle.programmar.Go.Expressions.Go_EmptyArrayExpression;
import com.eagle.programmar.Go.Expressions.Go_EqualityExpression;
import com.eagle.programmar.Go.Expressions.Go_FunctionCall;
import com.eagle.programmar.Go.Expressions.Go_LogicalAndExpression;
import com.eagle.programmar.Go.Expressions.Go_LogicalNotExpression;
import com.eagle.programmar.Go.Expressions.Go_LogicalOrExpression;
import com.eagle.programmar.Go.Expressions.Go_MultiplicativeExpression;
import com.eagle.programmar.Go.Expressions.Go_NegativeExpression;
import com.eagle.programmar.Go.Expressions.Go_ParenthesizedExpression;
import com.eagle.programmar.Go.Expressions.Go_PostIncrementExpression;
import com.eagle.programmar.Go.Expressions.Go_PreIncrementExpression;
import com.eagle.programmar.Go.Expressions.Go_RelationalExpression;
import com.eagle.programmar.Go.Expressions.Go_StarExpression;
import com.eagle.programmar.Go.Expressions.Go_SubscriptExpression;
import com.eagle.programmar.Go.Expressions.Go_VariableExpression;
import com.eagle.programmar.Go.Functions.Go_LenFunction;
import com.eagle.programmar.Go.Terminals.Go_Literal;
import com.eagle.programmar.Go.Terminals.Go_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Go_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Go_Expression()
	{
		super(_operators);
	}

	public Go_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Go_Number number;
	public @P(20) Go_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Go_LenFunction lenFunction;
	public @P(110) Go_FunctionCall functionCall;
	public @P(120) Go_PreIncrementExpression preIncrementExpression;
	public @P(130) Go_PostIncrementExpression postIncrementExpression;
	public @P(140) Go_NegativeExpression negativeExpression;
	public @P(150) Go_LogicalNotExpression logicalNotExpression;
	public @P(160) Go_BuiltIn builtIn;
	public @P(170) Go_VariableExpression variableExpression;
	public @P(180) Go_Constructor constructor;
	public @P(190) Go_BracesExpression bracesExpression;
	public @P(200) Go_ParenthesizedExpression parenthesizedExpression;
	public @P(210) Go_EmptyArrayExpression emptyArrayExpression;
	public @P(220) Go_StarExpression starExpression;
	public @P(230) Go_AmpersandExpression ampersandExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Go_SubscriptExpression subscriptExpression;
	public @P(1010) Go_MultiplicativeExpression multiplicativeExpression;
	public @P(1020) Go_AdditiveExpression additiveExpression;
	public @P(1030) Go_RelationalExpression relationalExpression;
	public @P(1040) Go_EqualityExpression equalityExpression;
	public @P(1050) Go_LogicalAndExpression conditionalAndExpression;
	public @P(1060) Go_LogicalOrExpression conditionalOrExpression;
	public @P(1070) Go_AssignmentExpression assignmentExpression;
}
