// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Regular_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_GrepExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword GREP = new Perl_Keyword("grep");
	public @S(20) Perl_Regular_Expression regex;
	public @S(30) PunctuationComma comma;
	public @S(40) Perl_Expression expr;
}
