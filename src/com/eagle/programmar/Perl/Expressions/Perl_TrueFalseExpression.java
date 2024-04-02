// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Perl_TrueFalseExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Perl_Punctuation questionMark = new Perl_Punctuation('?');
	public @S(30) Perl_Expression middle = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(40) PunctuationColon colon;
	public @S(50) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
}
