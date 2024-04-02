// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Terminals.Julia_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Julia_PostIncrementExpression extends PrimaryOperator
{
	public @S(10) Julia_Variable var;
	public @S(20) Julia_PunctuationChoice postIncrementOperator = new Julia_PunctuationChoice("++", "--");
}
