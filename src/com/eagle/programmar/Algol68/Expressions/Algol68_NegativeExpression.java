// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.Algol68.Expressions;

import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Algol68_NegativeExpression extends PrimaryOperator
{
	public @S(10) Algol68_PunctuationChoice operator = new Algol68_PunctuationChoice("-");
	public @S(20) Algol68_Expression expr;
}