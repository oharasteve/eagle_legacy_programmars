// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Go.Expressions;

import com.eagle.programmar.Go.Terminals.Go_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Go_BuiltIn extends PrimaryOperator
{
	public @S(10) Go_KeywordChoice builtinConstant = new Go_KeywordChoice("false", "true");
}
