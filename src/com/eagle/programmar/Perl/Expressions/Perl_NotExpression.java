// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;

public class Perl_NotExpression extends PrimaryOperator
{
	public @S(10) Perl_NotOperator oper;
	public @S(20) Perl_Expression expr;

	public static class Perl_NotOperator extends TokenChooser
	{
		public @CHOICE Perl_Punctuation notOperator = new Perl_Punctuation('!');
		public @CHOICE Perl_Keyword NOT = new Perl_Keyword("not");
	}
}
