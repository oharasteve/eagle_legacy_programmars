// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Perl_StarExpression extends PrimaryOperator
{
	public @S(10) PunctuationStar starOperator;
	public @S(20) Perl_Expression expr;
}
