// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Perl_ConditionalOrExpression extends PrecedenceOperator
{
	public @S(10) Perl_Expression left = new Perl_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Perl_OrOperator oper;
	public @S(30) Perl_Expression right = new Perl_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class Perl_OrOperator extends TokenChooser
	{
		public @CHOICE Perl_Punctuation orOperator = new Perl_Punctuation("||");
		public @CHOICE Perl_Keyword OR = new Perl_Keyword("or");
	}
}
