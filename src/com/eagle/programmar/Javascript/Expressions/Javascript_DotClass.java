// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Javascript_Type;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Javascript_DotClass extends PrimaryOperator
{
	public @S(10) Javascript_Type jtype;
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword CLASS = new Javascript_Keyword("class");
}
