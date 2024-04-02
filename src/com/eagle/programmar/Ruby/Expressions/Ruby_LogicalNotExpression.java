// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Terminals.Ruby_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Ruby_LogicalNotExpression extends PrimaryOperator
{
	public @S(10) Ruby_Punctuation logicalNotOperator = new Ruby_Punctuation('~');
	public @S(20) Ruby_Expression expr;
}
