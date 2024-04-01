// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.Terminals.C_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class C_BuiltIn extends PrimaryOperator
{
	public @S(10) C_KeywordChoice logicalConstant = new C_KeywordChoice("false", "true", "NULL", "default");
}
