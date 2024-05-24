// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class C_PostDecrementVariable extends PrimaryOperator
{
	public @S(10) C_Variable var; // Cannot be just C_Expression -- infinite loop
	public @S(20) C_Punctuation postDecrementOperator = new C_Punctuation("--");
}
