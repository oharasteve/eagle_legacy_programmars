// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Perl_PreIncrementExpression extends PrimaryOperator
{
	public @S(10) Perl_PunctuationChoice operator = new Perl_PunctuationChoice("++", "--");
	public @S(20) Perl_Variable var;
}
