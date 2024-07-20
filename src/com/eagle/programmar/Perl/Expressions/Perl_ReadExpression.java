// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Expression;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Perl_ReadExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword READ = new Perl_Keyword("read");
	public @S(20) Perl_Identifier_Reference file;
	public @S(30) PunctuationComma comma1;
	public @S(40) Perl_Expression buffer;
	public @S(50) PunctuationComma comma2;
	public @S(60) Perl_Expression size;
}
