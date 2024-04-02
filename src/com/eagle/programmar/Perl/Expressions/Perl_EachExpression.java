// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Perl_EachExpression extends PrimaryOperator
{
	public @S(10) Perl_Keyword EACH = new Perl_Keyword("each");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Perl_Variable var;
	public @S(40) PunctuationRightParen rightParen;
}
