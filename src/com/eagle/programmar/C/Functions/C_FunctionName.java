// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Functions;

import com.eagle.programmar.C.C_Generic;
import com.eagle.programmar.C.C_Variable;
import com.eagle.tokens.PrimaryOperator;

public class C_FunctionName extends PrimaryOperator
{
	public @S(10) C_Variable functionName;
	public @S(20) C_Generic generic;
}
