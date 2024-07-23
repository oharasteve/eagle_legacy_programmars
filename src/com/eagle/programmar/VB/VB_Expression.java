// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Expressions.VB_AdditiveExpression;
import com.eagle.programmar.VB.Expressions.VB_AndExpression;
import com.eagle.programmar.VB.Expressions.VB_ArrayExpression;
import com.eagle.programmar.VB.Expressions.VB_BuiltIn;
import com.eagle.programmar.VB.Expressions.VB_CommentExpression;
import com.eagle.programmar.VB.Expressions.VB_ConcatExpression;
import com.eagle.programmar.VB.Expressions.VB_ConditionalAndExpression;
import com.eagle.programmar.VB.Expressions.VB_ConditionalOrExpression;
import com.eagle.programmar.VB.Expressions.VB_EqualityExpression;
import com.eagle.programmar.VB.Expressions.VB_ExclusiveOrExpression;
import com.eagle.programmar.VB.Expressions.VB_ExponentExpression;
import com.eagle.programmar.VB.Expressions.VB_FunctionCall;
import com.eagle.programmar.VB.Expressions.VB_InclusiveOrExpression;
import com.eagle.programmar.VB.Expressions.VB_InstanceOfExpression;
import com.eagle.programmar.VB.Expressions.VB_MultiplicativeExpression;
import com.eagle.programmar.VB.Expressions.VB_NegativeExpression;
import com.eagle.programmar.VB.Expressions.VB_NotExpression;
import com.eagle.programmar.VB.Expressions.VB_ParenthesizedExpression;
import com.eagle.programmar.VB.Expressions.VB_RelationalExpression;
import com.eagle.programmar.VB.Expressions.VB_ShiftExpression;
import com.eagle.programmar.VB.Expressions.VB_Subfield;
import com.eagle.programmar.VB.Expressions.VB_SubscriptExpression;
import com.eagle.programmar.VB.Expressions.VB_VariableExpression;
import com.eagle.programmar.VB.Terminals.VB_Literal;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class VB_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public VB_Expression()
	{
		super(_operators);
	}

	public VB_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) VB_Number number;
	public @P(20) VB_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) VB_ArrayExpression arrayExpression;
	public @P(110) VB_BuiltIn builtIn;
	public @P(120) VB_FunctionCall functionCall;
	public @P(130) VB_NegativeExpression negativeExpression;
	public @P(140) VB_NotExpression notExpression;
	public @P(150) VB_VariableExpression variableExpression;
	public @P(160) VB_ParenthesizedExpression parenthesizedExpression;
	public @P(170) VB_CommentExpression commentExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) VB_SubscriptExpression subscriptExpression;
	public @P(510) VB_Subfield subfield;
	public @P(520) VB_ExponentExpression exponentExpression;
	public @P(530) VB_MultiplicativeExpression multiplicativeExpression;
	public @P(540) VB_AdditiveExpression additiveExpression;
	public @P(550) VB_ConcatExpression concatExpression;
	public @P(560) VB_ShiftExpression shiftExpression;
	public @P(570) VB_RelationalExpression relationalExpression;
	public @P(580) VB_InstanceOfExpression instanceOfExpression;
	public @P(590) VB_EqualityExpression equalityExpression;
	public @P(600) VB_AndExpression andExpression;
	public @P(610) VB_ExclusiveOrExpression exclusiveOrExpression;
	public @P(620) VB_InclusiveOrExpression inclusiveOrExpression;
	public @P(630) VB_ConditionalAndExpression conditionalAndExpression;
	public @P(640) VB_ConditionalOrExpression conditionalOrExpression;
}
