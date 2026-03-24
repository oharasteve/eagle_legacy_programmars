// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Expressions.PLI_AdditiveExpression;
import com.eagle.programmar.PLI.Expressions.PLI_CommentExpression;
import com.eagle.programmar.PLI.Expressions.PLI_ExponentExpression;
import com.eagle.programmar.PLI.Expressions.PLI_FieldReference;
import com.eagle.programmar.PLI.Expressions.PLI_LogicalAndExpression;
import com.eagle.programmar.PLI.Expressions.PLI_LogicalAndThenExpression;
import com.eagle.programmar.PLI.Expressions.PLI_LogicalNotExpression;
import com.eagle.programmar.PLI.Expressions.PLI_LogicalOrElseExpression;
import com.eagle.programmar.PLI.Expressions.PLI_LogicalOrExpression;
import com.eagle.programmar.PLI.Expressions.PLI_MultiplicativeExpression;
import com.eagle.programmar.PLI.Expressions.PLI_NegativeExpression;
import com.eagle.programmar.PLI.Expressions.PLI_ParenthesizedExpression;
import com.eagle.programmar.PLI.Expressions.PLI_RelationalExpression;
import com.eagle.programmar.PLI.Expressions.PLI_RepeatedBitLiteral;
import com.eagle.programmar.PLI.Expressions.PLI_RepeatedHexLiteral;
import com.eagle.programmar.PLI.Expressions.PLI_RepeatedLiteral;
import com.eagle.programmar.PLI.Expressions.PLI_StrCatExpression;
import com.eagle.programmar.PLI.Expressions.PLI_VariableOrFunctionCall;
import com.eagle.programmar.PLI.Functions.PLI_LengthFunction;
import com.eagle.programmar.PLI.Functions.PLI_ModFunction;
import com.eagle.programmar.PLI.Functions.PLI_SubstrFunction;
import com.eagle.programmar.PLI.Functions.PLI_TrimFunction;
import com.eagle.programmar.PLI.Functions.PLI_TruncFunction;
import com.eagle.programmar.PLI.Terminals.PLI_BitLiteral;
import com.eagle.programmar.PLI.Terminals.PLI_HexNumber;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class PLI_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public PLI_Expression()
	{
		super(_operators);
	}

	public PLI_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) PLI_Number number;
	public @P(20) PLI_BitLiteral bits;
	public @P(30) PLI_HexNumber hex;
	public @P(40) PLI_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) PLI_RepeatedBitLiteral repeatedBitLiteral;
	public @P(110) PLI_RepeatedHexLiteral repeatedHexLiteral;
	public @P(120) PLI_RepeatedLiteral repeatedLiteral;
	public @P(130) PLI_NegativeExpression negativeExpression;
	public @P(140) PLI_LogicalNotExpression notExpression;
	public @P(150) PLI_FieldReference fieldReference;
	public @P(160) PLI_LengthFunction lengthFunction;
	public @P(170) PLI_ModFunction modFunction;
	public @P(180) PLI_SubstrFunction substrFunction;
	public @P(190) PLI_TrimFunction trimFunction;
	public @P(200) PLI_TruncFunction truncFunction;
	public @P(210) PLI_VariableOrFunctionCall variableOrFunctionCall;
	public @P(220) PLI_ParenthesizedExpression parenthesizedExpression;
	public @P(230) PLI_CommentExpression commentExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) PLI_ExponentExpression exponentExpression;
	public @P(1010) PLI_MultiplicativeExpression multiplicativeExpression;
	public @P(1020) PLI_AdditiveExpression additiveExpression;
	public @P(1030) PLI_StrCatExpression strCatExpression;
	public @P(1040) PLI_RelationalExpression relationalExpression;
	public @P(1050) PLI_LogicalAndExpression andExpression;
	public @P(1060) PLI_LogicalOrExpression orExpression;
	public @P(1070) PLI_LogicalAndThenExpression andThenExpression;
	public @P(1080) PLI_LogicalOrElseExpression orElseExpression;
}
