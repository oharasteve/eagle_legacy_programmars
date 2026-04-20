// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68;

import com.eagle.programmar.Algol68.Expressions.Algol68_AdditiveExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_ArrayInfo;
import com.eagle.programmar.Algol68.Expressions.Algol68_AssignmentExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_BracketsExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_BuiltIn;
import com.eagle.programmar.Algol68.Expressions.Algol68_LogicalAndExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_LogicalNotExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_LogicalOrExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_MultiplicativeExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_NegativeExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_ParenthesizedExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_PostIncrementExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_Power_Expression;
import com.eagle.programmar.Algol68.Expressions.Algol68_PreIncrementExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_ProcedureCall;
import com.eagle.programmar.Algol68.Expressions.Algol68_RangeExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_RelationalExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_Subfield;
import com.eagle.programmar.Algol68.Expressions.Algol68_SubscriptExpression;
import com.eagle.programmar.Algol68.Expressions.Algol68_VariableExpression;
import com.eagle.programmar.Algol68.Functions.Algol68_EntierFunction;
import com.eagle.programmar.Algol68.Functions.Algol68_FixedFunction;
import com.eagle.programmar.Algol68.Functions.Algol68_WholeFunction;
import com.eagle.programmar.Algol68.Terminals.Algol68_Literal;
import com.eagle.programmar.Algol68.Terminals.Algol68_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Algol68_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Algol68_Expression()
	{
		super(_operators);
	}

	public Algol68_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Algol68_Number number;
	public @P(20) Algol68_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Algol68_ArrayInfo arrayInfo;
	public @P(110) Algol68_PreIncrementExpression preIncrementExpression;
	public @P(120) Algol68_PostIncrementExpression postIncrementExpression;
	public @P(130) Algol68_NegativeExpression negativeExpression;
	public @P(140) Algol68_LogicalNotExpression logicalNotExpression;
	public @P(150) Algol68_EntierFunction entierExpression;
	public @P(160) Algol68_WholeFunction wholeExpression;
	public @P(170) Algol68_FixedFunction fixedExpression;
	public @P(180) Algol68_BuiltIn builtIn;
	public @P(190) Algol68_ProcedureCall methodInvocation;
	public @P(200) Algol68_VariableExpression variableExpression;
	public @P(210) Algol68_BracketsExpression bracketsExpression;
	public @P(220) Algol68_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Algol68_SubscriptExpression subscriptExpression;
	public @P(1010) Algol68_Subfield subfield;
	public @P(1020) Algol68_Power_Expression powerExpression;
	public @P(1030) Algol68_MultiplicativeExpression multiplicativeExpression;
	public @P(1040) Algol68_AdditiveExpression additiveExpression;
	public @P(1050) Algol68_RelationalExpression relationalExpression;
	public @P(1060) Algol68_LogicalAndExpression conditionalAndExpression;
	public @P(1070) Algol68_LogicalOrExpression conditionalOrExpression;
	public @P(1080) Algol68_AssignmentExpression assignmentExpression;
	public @P(1090) Algol68_RangeExpression rangeExpression;
}
