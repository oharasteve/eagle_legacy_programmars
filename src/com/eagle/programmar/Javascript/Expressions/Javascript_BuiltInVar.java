// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Expressions;

import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Javascript_BuiltInVar extends PrimaryOperator
{
	public @S(10) Javascript_KeywordChoice logicalConstant = new Javascript_KeywordChoice(
			"arguments",
			"false",
			"null",
			"String",
			"super",
			"this",
			"true"
		);
}
