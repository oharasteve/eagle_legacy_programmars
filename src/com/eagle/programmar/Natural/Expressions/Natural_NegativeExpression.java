// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Natural.Expressions;

import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Natural_NegativeExpression extends PrimaryOperator
{
	public @S(10) Natural_PunctuationChoice plusMinus = new Natural_PunctuationChoice("+", "-");
	public @S(20) Natural_Expression expr;
}
