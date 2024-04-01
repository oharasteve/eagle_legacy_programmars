// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_Star_Expression extends PrimaryOperator
{
	public @S(10) PunctuationStar star;
	public @S(20) Python_Expression expr;
}
