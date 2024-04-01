// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class C_AddressOfVariable extends PrimaryOperator
{
	public @S(10) C_Punctuation ampersand = new C_Punctuation('&');
	public @S(20) C_Expression expr;
}
