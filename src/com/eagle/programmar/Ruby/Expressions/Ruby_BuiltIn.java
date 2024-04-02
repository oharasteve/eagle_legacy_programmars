// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Ruby.Expressions;

import com.eagle.programmar.Ruby.Terminals.Ruby_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Ruby_BuiltIn extends PrimaryOperator
{
	public @S(10) Ruby_KeywordChoice builtinConstant = new Ruby_KeywordChoice("false", "true");
}
