// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_DollarParensExpression extends PrimaryOperator
{
	public @S(10) AWK_Punctuation dollar = new AWK_Punctuation('$');
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) AWK_Expression expression;
	public @S(40) PunctuationRightParen rightParen;
}
