// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Algol68_BracketsExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) SeparatedList<Algol68_Expression, PunctuationComma> expression;
	public @S(30) PunctuationRightBracket rightBracket;
}
