// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Perl_BitwiseNotExpression extends PrimaryOperator
{
	public @S(10) Perl_Punctuation logicalNotOperator = new Perl_Punctuation('~');
	public @S(20) Perl_Expression expr;
}
