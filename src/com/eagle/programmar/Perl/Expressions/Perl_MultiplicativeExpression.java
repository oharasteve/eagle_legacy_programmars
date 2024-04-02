// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Perl_MultiplicativeExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_PunctuationChoice operator = new Perl_PunctuationChoice("*", "/", "%");
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
}
