// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class PLI_NegativeExpression extends PrimaryOperator
{
	public @S(10) PLI_PunctuationChoice operator = new PLI_PunctuationChoice("-", "+");
	public @S(20) PLI_Expression expr;
}
