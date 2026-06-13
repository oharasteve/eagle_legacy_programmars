// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2026

package com.eagle.programmar.Haskell.Expressions;

import com.eagle.programmar.Haskell.Haskell_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Haskell_TupleExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Haskell_Expression first;
	public @S(30) PunctuationComma comma;
	public @S(40) SeparatedList<Haskell_Expression,PunctuationComma> more;
	public @S(50) PunctuationRightParen rightParen;
}
