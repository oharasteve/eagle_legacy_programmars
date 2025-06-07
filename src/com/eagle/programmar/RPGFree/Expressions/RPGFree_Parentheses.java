// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Expressions;

import com.eagle.programmar.Rexx.Rexx_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class RPGFree_Parentheses extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Rexx_Expression expression;
	public @S(30) PunctuationRightParen rightParen;
}
