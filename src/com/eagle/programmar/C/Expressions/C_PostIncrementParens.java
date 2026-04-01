// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2026

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class C_PostIncrementParens extends PrimaryOperator
{
	public @S(10) C_Parenthesized_Expression expr; // Cannot be just C_Expression -- infinite loop
	public @S(20) C_PunctuationChoice operator = new C_PunctuationChoice("++", "--");
}
