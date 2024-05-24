// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Expressions.Gupta_Additive_Expression;
import com.eagle.programmar.Gupta.Expressions.Gupta_FunctionCall;
import com.eagle.programmar.Gupta.Expressions.Gupta_IdentifierExpression;
import com.eagle.programmar.Gupta.Expressions.Gupta_Multiplicative_Expression;
import com.eagle.programmar.Gupta.Expressions.Gupta_Parens;
import com.eagle.programmar.Gupta.Expressions.Gupta_StrCat_Expression;
import com.eagle.programmar.Gupta.Expressions.Gupta_UnarySign;
import com.eagle.programmar.Gupta.Terminals.Gupta_Literal;
import com.eagle.programmar.Gupta.Terminals.Gupta_Number;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;

public class Gupta_Expression extends PrecedenceChooser
{
	private static OperatorList _operators = new OperatorList();

	public Gupta_Expression()
	{
		super(_operators);
	}

	public Gupta_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
		super(_operators, allowed, token.getClass());
	}

	//
	// Note: All fields should stay in @P(#) order. The # determines operator
	// precedence.
	//

	///////////////////////////////////////////////
	// Terminals

	public @P(10) Gupta_Number number;
	public @P(20) Gupta_Literal literal;

	///////////////////////////////////////////////////////////////////////////
	// Primary Expressions

	public @P(100) Gupta_Parens parens;
	public @P(110) Gupta_FunctionCall functionCall;
	public @P(120) Gupta_IdentifierExpression identifierExpression;
	public @P(130) Gupta_UnarySign unarySign;

	///////////////////////////////////////////////////////////////////////////
	// Binary Expressions

	public @P(500) Gupta_Multiplicative_Expression multiplicative_Expression;
	public @P(510) Gupta_Additive_Expression additive_Expression;
	public @P(520) Gupta_StrCat_Expression strCat_Expression;
}
