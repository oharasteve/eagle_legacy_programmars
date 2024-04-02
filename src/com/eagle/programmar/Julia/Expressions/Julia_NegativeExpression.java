// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Julia_NegativeExpression extends PrimaryOperator
{
	public @S(10) Julia_PunctuationChoice operator = new Julia_PunctuationChoice("-");
	public @S(20) Julia_Expression expr;
}
