package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class VB_ConditionalAndExpression extends PrecedenceOperator
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_KeywordChoice andOperator = new VB_KeywordChoice("and", "andalso");
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);
}
