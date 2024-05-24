// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_JoinExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword JOIN = new Perl_Keyword("join");
	public @S(20) SeparatedList<Perl_Expression, PunctuationComma> items;
}
