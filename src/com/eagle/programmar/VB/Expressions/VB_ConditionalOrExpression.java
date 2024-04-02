package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class VB_ConditionalOrExpression extends PrecedenceOperator
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_KeywordChoice orOperator = new VB_KeywordChoice("or", "orelse");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
}
