// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_NegativeExpression extends PrimaryOperator
{
	public @S(10) CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("-", "+");
	public @S(20) CSharp_Expression expr;
}
