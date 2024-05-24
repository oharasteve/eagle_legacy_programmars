// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala;

import com.eagle.programmar.Scala.Expressions.Scala_AdditiveExpression;
import com.eagle.programmar.Scala.Expressions.Scala_AssignmentExpression;
import com.eagle.programmar.Scala.Expressions.Scala_BracesExpression;
import com.eagle.programmar.Scala.Expressions.Scala_BuiltIn;
import com.eagle.programmar.Scala.Expressions.Scala_ConditionalAndExpression;
import com.eagle.programmar.Scala.Expressions.Scala_ConditionalOrExpression;
import com.eagle.programmar.Scala.Expressions.Scala_EqualityExpression;
import com.eagle.programmar.Scala.Expressions.Scala_LogicalNotExpression;
import com.eagle.programmar.Scala.Expressions.Scala_MethodInvocation;
import com.eagle.programmar.Scala.Expressions.Scala_MultiplicativeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_NegativeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_NotExpression;
import com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression;
import com.eagle.programmar.Scala.Expressions.Scala_PostIncrementExpression;
import com.eagle.programmar.Scala.Expressions.Scala_PreIncrementExpression;
import com.eagle.programmar.Scala.Expressions.Scala_RangeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_RelationalExpression;
import com.eagle.programmar.Scala.Expressions.Scala_Subfield;
import com.eagle.programmar.Scala.Expressions.Scala_SubscriptExpression;
import com.eagle.programmar.Scala.Expressions.Scala_VariableExpression;
import com.eagle.programmar.Scala.Terminals.Scala_Literal;
import com.eagle.programmar.Scala.Terminals.Scala_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class Scala_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public Scala_Expression()
	{
		super(_operators);
	}

	public Scala_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Scala_Number number;
	public @P(20) Scala_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) Scala_MethodInvocation methodInvocation;
	public @P(110) Scala_PreIncrementExpression preIncrementExpression;
	public @P(120) Scala_PostIncrementExpression postIncrementExpression;
	public @P(130) Scala_NegativeExpression negativeExpression;
	public @P(140) Scala_LogicalNotExpression logicalNotExpression;
	public @P(150) Scala_NotExpression notExpression;
	public @P(160) Scala_BuiltIn builtIn;
	public @P(170) Scala_VariableExpression variableExpression;
	public @P(180) Scala_BracesExpression bracesExpression;
	public @P(190) Scala_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) Scala_SubscriptExpression subscriptExpression;
	public @P(510) Scala_Subfield subfield;
	public @P(520) Scala_MultiplicativeExpression multiplicativeExpression;
	public @P(530) Scala_AdditiveExpression additiveExpression;
	public @P(540) Scala_RelationalExpression relationalExpression;
	public @P(550) Scala_EqualityExpression equalityExpression;
	public @P(560) Scala_ConditionalAndExpression conditionalAndExpression;
	public @P(570) Scala_ConditionalOrExpression conditionalOrExpression;
	public @P(580) Scala_AssignmentExpression assignmentExpression;
	public @P(590) Scala_RangeExpression rangeExpression;
}
