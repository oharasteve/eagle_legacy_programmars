// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Julia_NotExpression extends PrimaryOperator
{
	public @S(10) Julia_Punctuation notOperator = new Julia_Punctuation('!');
	public @S(20) Julia_Expression expr;
}
