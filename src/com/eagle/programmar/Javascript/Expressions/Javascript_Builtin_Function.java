// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_Builtin_Function extends PrimaryOperator
{
	public @S(10) Javascript_KeywordChoice fn = new Javascript_KeywordChoice("eval");
}
