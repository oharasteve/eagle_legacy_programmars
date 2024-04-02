// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Perl_ExistsExpression extends PrimaryOperator
{
	public @S(10) PunctuationHyphen minus;
	public @S(20) Perl_KeywordChoice XE = new Perl_KeywordChoice("e", "x");
	public @S(30) Perl_Expression file;
}
