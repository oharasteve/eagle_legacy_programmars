// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationAmpersand;

public class Go_AmpersandExpression extends PrimaryOperator
{
	public @S(10) PunctuationAmpersand ampersand;
	public @S(20) Go_Expression expression;
}
