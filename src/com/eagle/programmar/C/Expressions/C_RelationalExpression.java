// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class C_RelationalExpression extends PrecedenceOperator
{
	public @S(10) C_Expression left = new C_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("<", ">", "<=", ">=");
	public @S(30) C_Expression rightRel = new C_Expression(this, AllowedPrecedence.HIGHER);
}
