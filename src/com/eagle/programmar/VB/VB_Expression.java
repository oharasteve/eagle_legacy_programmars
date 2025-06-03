// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Expressions.VB_AdditiveExpression;
import com.eagle.programmar.VB.Expressions.VB_ArrayExpression;
import com.eagle.programmar.VB.Expressions.VB_BitwiseAndExpression;
import com.eagle.programmar.VB.Expressions.VB_BuiltIn;
import com.eagle.programmar.VB.Expressions.VB_CommentExpression;
import com.eagle.programmar.VB.Expressions.VB_ConcatExpression;
import com.eagle.programmar.VB.Expressions.VB_EqualityExpression;
import com.eagle.programmar.VB.Expressions.VB_ExponentExpression;
import com.eagle.programmar.VB.Expressions.VB_InstanceOfExpression;
import com.eagle.programmar.VB.Expressions.VB_LogicalAndExpression;
import com.eagle.programmar.VB.Expressions.VB_LogicalNotExpression;
import com.eagle.programmar.VB.Expressions.VB_LogicalOrExpression;
import com.eagle.programmar.VB.Expressions.VB_LogicalXorExpression;
import com.eagle.programmar.VB.Expressions.VB_MultiplicativeExpression;
import com.eagle.programmar.VB.Expressions.VB_NegativeExpression;
import com.eagle.programmar.VB.Expressions.VB_ParenthesizedExpression;
import com.eagle.programmar.VB.Expressions.VB_RelationalExpression;
import com.eagle.programmar.VB.Expressions.VB_ShiftExpression;
import com.eagle.programmar.VB.Expressions.VB_Subfield;
import com.eagle.programmar.VB.Expressions.VB_VariableExpression;
import com.eagle.programmar.VB.Functions.VB_CStrFunction;
import com.eagle.programmar.VB.Functions.VB_FunctionCall;
import com.eagle.programmar.VB.Functions.VB_LenFunction;
import com.eagle.programmar.VB.Functions.VB_MidFunction;
import com.eagle.programmar.VB.Functions.VB_UcaseFunction;
import com.eagle.programmar.VB.Terminals.VB_Literal;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class VB_Expression extends PrecedenceChooser implements AbstractExpression
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
	public @P(120) VB_MidFunction midFunction;
	public @P(130) VB_LenFunction lenFunction;
	public @P(140) VB_UcaseFunction ucaseFunction;
	public @P(150) VB_CStrFunction cstrFunction;
	public @P(160) VB_FunctionCall functionCall;
	public @P(170) VB_NegativeExpression negativeExpression;
	public @P(180) VB_LogicalNotExpression notExpression;
	public @P(190) VB_VariableExpression variableExpression;
	public @P(200) VB_ParenthesizedExpression parenthesizedExpression;
	public @P(210) VB_CommentExpression commentExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) VB_Subfield subfield;
	public @P(1010) VB_ExponentExpression exponentExpression;
	public @P(1020) VB_MultiplicativeExpression multiplicativeExpression;
	public @P(1030) VB_AdditiveExpression additiveExpression;
	public @P(1040) VB_ConcatExpression concatExpression;
	public @P(1050) VB_ShiftExpression shiftExpression;
	public @P(1060) VB_RelationalExpression relationalExpression;
	public @P(1070) VB_InstanceOfExpression instanceOfExpression;
	public @P(1080) VB_EqualityExpression equalityExpression;
	public @P(1090) VB_BitwiseAndExpression andExpression;
	public @P(1100) VB_LogicalXorExpression inclusiveOrExpression;
	public @P(1110) VB_LogicalAndExpression conditionalAndExpression;
	public @P(1120) VB_LogicalOrExpression conditionalOrExpression;
}
