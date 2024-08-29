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
import com.eagle.programmar.Scala.Expressions.Scala_FunctionCall;
import com.eagle.programmar.Scala.Expressions.Scala_LogicalNotExpression;
import com.eagle.programmar.Scala.Expressions.Scala_MultiplicativeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_NegativeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_NotExpression;
import com.eagle.programmar.Scala.Expressions.Scala_ParenthesizedExpression;
import com.eagle.programmar.Scala.Expressions.Scala_PostIncrementExpression;
import com.eagle.programmar.Scala.Expressions.Scala_PreIncrementExpression;
import com.eagle.programmar.Scala.Expressions.Scala_RangeExpression;
import com.eagle.programmar.Scala.Expressions.Scala_RelationalExpression;
import com.eagle.programmar.Scala.Expressions.Scala_VariableExpression;
import com.eagle.programmar.Scala.Functions.Scala_EqualsMethod;
import com.eagle.programmar.Scala.Functions.Scala_LengthMethod;
import com.eagle.programmar.Scala.Functions.Scala_ListFunction;
import com.eagle.programmar.Scala.Functions.Scala_PrintLnFunction;
import com.eagle.programmar.Scala.Functions.Scala_ReverseMethod;
import com.eagle.programmar.Scala.Functions.Scala_StartsWithMethod;
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

	public @P(100) Scala_FunctionCall functionCall;
	public @P(110) Scala_PreIncrementExpression preIncrementExpression;
	public @P(120) Scala_PostIncrementExpression postIncrementExpression;
	public @P(130) Scala_NegativeExpression negativeExpression;
	public @P(140) Scala_LogicalNotExpression logicalNotExpression;
	public @P(150) Scala_NotExpression notExpression;
	public @P(160) Scala_BuiltIn builtIn;
	public @P(170) Scala_ListFunction listFunction;
	public @P(180) Scala_PrintLnFunction printlnFunction;
	public @P(190) Scala_VariableExpression variableExpression;
	public @P(200) Scala_BracesExpression bracesExpression;
	public @P(210) Scala_ReverseMethod rangeREversed;
	public @P(220) Scala_ParenthesizedExpression parenthesizedExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) Scala_EqualsMethod equalsMethod;
	public @P(1010) Scala_LengthMethod lengthMethod;
	public @P(1020) Scala_StartsWithMethod startswithMethod;
	public @P(1030) Scala_MultiplicativeExpression multiplicativeExpression;
	public @P(1040) Scala_AdditiveExpression additiveExpression;
	public @P(1050) Scala_RelationalExpression relationalExpression;
	public @P(1060) Scala_EqualityExpression equalityExpression;
	public @P(1070) Scala_ConditionalAndExpression conditionalAndExpression;
	public @P(1080) Scala_ConditionalOrExpression conditionalOrExpression;
	public @P(1090) Scala_AssignmentExpression assignmentExpression;
	public @P(1100) Scala_RangeExpression rangeExpression;
}
