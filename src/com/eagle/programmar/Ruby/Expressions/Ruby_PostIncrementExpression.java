// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Terminals.Ruby_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Ruby_PostIncrementExpression extends PrimaryOperator
{
	public @S(10) Ruby_Variable var;
	public @S(20) Ruby_PunctuationChoice postIncrementOperator = new Ruby_PunctuationChoice("++", "--");
}
