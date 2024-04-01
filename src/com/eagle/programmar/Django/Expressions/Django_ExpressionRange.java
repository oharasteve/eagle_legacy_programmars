// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Django.Expressions;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Number;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Django_ExpressionRange extends PrimaryOperator
{
	public @S(10) Django_Keyword RANGE = new Django_Keyword("range");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) HTML_Number number;
	public @S(40) PunctuationRightParen rightParen;
}
