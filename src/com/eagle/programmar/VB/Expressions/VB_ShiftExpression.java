// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class VB_ShiftExpression extends PrecedenceOperator
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_PunctuationChoice operator = new VB_PunctuationChoice("<<", ">>");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
}
