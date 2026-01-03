// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2026

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrecedenceOperator;

public class Perl_RangeExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Perl_Punctuation dots = new Perl_Punctuation("..");
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
}