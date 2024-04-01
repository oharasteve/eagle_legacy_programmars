// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationColon;

public class AWK_TrueFalseExpression extends PrecedenceOperator
{
	public @S(10) AWK_Expression left = new AWK_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) AWK_Punctuation questionMark = new AWK_Punctuation('?');
	public @S(30) AWK_Expression middle = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(40) PunctuationColon colon;
	public @S(50) AWK_Expression right = new AWK_Expression(this, AllowedPrecedence.ATLEAST);
}
