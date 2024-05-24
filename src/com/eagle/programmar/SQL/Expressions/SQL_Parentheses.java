// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.SQL.Expressions;

import com.eagle.programmar.SQL.SQL_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class SQL_Parentheses extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SeparatedList<SQL_Expression, PunctuationComma> exprs;
	public @S(30) PunctuationRightParen rightParen;
}