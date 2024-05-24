// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Django_FunctionExpression extends PrimaryOperator
{
	public @S(10) Django_Variable var;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT SeparatedList<Django_Expression, PunctuationComma> exprs;
	public @S(40) PunctuationRightParen rightParen;
}
