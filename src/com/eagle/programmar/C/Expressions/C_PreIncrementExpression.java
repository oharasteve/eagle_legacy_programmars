// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class C_PreIncrementExpression extends PrimaryOperator
{
	public @S(10) C_PunctuationChoice operator = new C_PunctuationChoice("++", "--");
	public @S(20) C_Expression expr;
}
