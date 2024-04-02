// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Perl_PreDecrementExpression extends PrimaryOperator
{
	public @S(10) Perl_Punctuation preDecrementOperator = new Perl_Punctuation("--");
	public @S(20) Perl_Variable var;
}
