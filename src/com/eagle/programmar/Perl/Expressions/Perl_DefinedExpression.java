// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 13, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.PrimaryOperator;

public class Perl_DefinedExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword DEFINED = new Perl_Keyword("defined");
	public @S(20) Perl_Expression what;
}
