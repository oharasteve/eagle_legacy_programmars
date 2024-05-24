// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Go_BracesExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) SeparatedList<Go_Expression, PunctuationComma> expression;
	public @S(30) PunctuationRightBrace rightBrace;
}
