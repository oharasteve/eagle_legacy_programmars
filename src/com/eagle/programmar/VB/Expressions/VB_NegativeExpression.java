// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class VB_NegativeExpression extends PrimaryOperator
{
	public @S(10) VB_PunctuationChoice operator = new VB_PunctuationChoice("-", "+");
	public @S(20) VB_Expression expr;
}
