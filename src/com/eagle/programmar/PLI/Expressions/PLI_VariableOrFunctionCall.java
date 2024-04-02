// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.PLI.Expressions;

import com.eagle.programmar.PLI.PLI_Subscript;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;

public class PLI_VariableOrFunctionCall extends PrimaryOperator
{
	public @S(10) PLI_Identifier_Reference fn;
	public @S(20) @OPT PLI_Subscript subscript;
}
