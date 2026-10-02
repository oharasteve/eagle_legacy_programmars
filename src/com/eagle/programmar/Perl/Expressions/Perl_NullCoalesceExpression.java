// Copyright Eagle Legacy Modernization LLC, 2010-date
// Added on the shane branch, Oct 2, 2026: PHP 7's a ?? b.

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Perl_NullCoalesceExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_Punctuation coalesce = new Perl_Punctuation("??");
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
}
