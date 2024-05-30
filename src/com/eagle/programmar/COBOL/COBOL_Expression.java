// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Expressions.COBOL_AdditiveExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_AddressExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_AndCondition;
import com.eagle.programmar.COBOL.Expressions.COBOL_BuiltIn;
import com.eagle.programmar.COBOL.Expressions.COBOL_ClassCondition;
import com.eagle.programmar.COBOL.Expressions.COBOL_ConcatenateExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_ExponentExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_ExpressionFunction;
import com.eagle.programmar.COBOL.Expressions.COBOL_IsType;
import com.eagle.programmar.COBOL.Expressions.COBOL_LengthExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_LinageCounterExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_MultiplicativeExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_NotCondition;
import com.eagle.programmar.COBOL.Expressions.COBOL_OrCondition;
import com.eagle.programmar.COBOL.Expressions.COBOL_ParenthesizedExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_RelationCondition;
import com.eagle.programmar.COBOL.Expressions.COBOL_SignedExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_ThroughExpression;
import com.eagle.programmar.COBOL.Expressions.COBOL_VariableExpression;
import com.eagle.programmar.COBOL.Terminals.COBOL_HexNumber;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.interfaces.AbstractExpression;

public class COBOL_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public COBOL_Expression()
	{
		super(_operators);
	}

	public COBOL_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) COBOL_Literal literal;
	public @P(20) COBOL_Number number;
	public @P(30) COBOL_HexNumber hex;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) COBOL_LengthExpression lengthExpression;
	public @P(110) COBOL_AddressExpression addressExpression;
	public @P(120) COBOL_LinageCounterExpression linageCounterExpression;
	public @P(130) COBOL_BuiltIn builtIn;
	public @P(140) COBOL_ParenthesizedExpression parenthesizedExpression;
	public @P(150) COBOL_ExpressionFunction expressionFunction;
	public @P(160) COBOL_ClassCondition classCondition;
	public @P(170) COBOL_SignedExpression signedExpression;
	public @P(180) COBOL_NotCondition notCondition;
	public @P(190) COBOL_VariableExpression variableExpression;

	///////////////////////////////////////////////
	// Binary expressions

	public @P(500) COBOL_ThroughExpression throughExpression;
	public @P(510) COBOL_ConcatenateExpression concatenateExpression;
	public @P(520) COBOL_ExponentExpression exponentExpression;
	public @P(530) COBOL_MultiplicativeExpression multiplicativeExpression;
	public @P(540) COBOL_AdditiveExpression additiveExpression;
	public @P(550) COBOL_RelationCondition relationCondition;
	public @P(560) COBOL_AndCondition andCondition;
	public @P(570) COBOL_OrCondition orCondition;
	public @P(580) COBOL_IsType isType;
}
