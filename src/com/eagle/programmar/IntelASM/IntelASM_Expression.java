// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 16, 2022

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Expressions.IntelASM_AdditiveExpression;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_Brackets;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_BytePtr;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_Dollar;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_DwordPtr;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_MultiplicativeExpression;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_RegisterExpr;
import com.eagle.programmar.IntelASM.Expressions.IntelASM_VariableExpr;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_HexNumber;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Literal;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class IntelASM_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public IntelASM_Expression()
	{
		super(_operators);
	}

	public IntelASM_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All operators should stay in @P(#) order.
	// This determines operator precedence.
	//

	public @P(10) IntelASM_HexNumber hex;
	public @P(20) IntelASM_Number number;
	public @P(30) IntelASM_Literal literal;

	///////////////////////////////////////////////
	// Primary expressions

	public @P(100) IntelASM_RegisterExpr register;
	public @P(110) IntelASM_VariableExpr var;
	public @P(120) IntelASM_Brackets brackets;
	public @P(130) IntelASM_BytePtr bytePtr;
	public @P(140) IntelASM_DwordPtr dwordPtr;
	public @P(150) IntelASM_Dollar dollar;
	
	///////////////////////////////////////////////
	// Binary expressions

	public @P(1000) IntelASM_MultiplicativeExpression multExpr;
	public @P(1010) IntelASM_AdditiveExpression addExpr;
}
