// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class AWK_ParenthesizedExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) AWK_Expression expression;
	public @S(30) PunctuationRightParen rightParen;
}
