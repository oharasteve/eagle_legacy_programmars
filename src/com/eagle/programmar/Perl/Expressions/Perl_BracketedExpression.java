// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.programmar.Perl.Perl_ArgumentList;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Perl_BracketedExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) @OPT TokenList<Perl_Comment> comment1;
	public @S(30) @OPT Perl_ArgumentList valueList;
	public @S(40) @OPT PunctuationComma comma;
	public @S(50) @OPT TokenList<Perl_Comment> comment2;
	public @S(60) PunctuationRightBracket rightBracket;
}
