// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Expressions.Django_AndExpression;
import com.eagle.programmar.Django.Expressions.Django_BarExpression;
import com.eagle.programmar.Django.Expressions.Django_BracketsExpression;
import com.eagle.programmar.Django.Expressions.Django_DefinedExpression;
import com.eagle.programmar.Django.Expressions.Django_EqualityExpression;
import com.eagle.programmar.Django.Expressions.Django_ExpressionRange;
import com.eagle.programmar.Django.Expressions.Django_FormatExpression;
import com.eagle.programmar.Django.Expressions.Django_FunctionExpression;
import com.eagle.programmar.Django.Expressions.Django_InExpression;
import com.eagle.programmar.Django.Expressions.Django_NotExpression;
import com.eagle.programmar.Django.Expressions.Django_OrExpression;
import com.eagle.programmar.Django.Expressions.Django_ParensExpression;
import com.eagle.programmar.Django.Expressions.Django_SubscriptExpression;
import com.eagle.programmar.Django.Expressions.Django_VariableExpression;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.Django.Terminals.Django_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Django_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Django_Expression()
	{
	    super(_operators);
	}

	public Django_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	//
	// Note: All fields should stay in @P(#) order. The # determines operator precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Django_Number number;
	public @P(20) Django_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions
	
	public @P(100) Django_ExpressionRange expressionRange;
	public @P(110) Django_NotExpression notExpression;
	public @P(120) Django_DefinedExpression definedExpression;
	public @P(130) Django_BracketsExpression bracketsExpression;
	public @P(140) Django_ParensExpression parensExpression;
	public @P(150) Django_FunctionExpression functionExpression;
	public @P(160) Django_VariableExpression variableExpression;
	
	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Django_SubscriptExpression subscriptExpression;
	public @P(510) Django_BarExpression barExpression;
	public @P(520) Django_EqualityExpression equalityExpression;
	public @P(530) Django_FormatExpression formatExpression;
	public @P(540) Django_InExpression inExpression;
	public @P(550) Django_AndExpression andExpression;
	public @P(560) Django_OrExpression orExpression;
}
