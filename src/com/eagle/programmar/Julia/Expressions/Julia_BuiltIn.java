// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Terminals.Julia_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Julia_BuiltIn extends PrimaryOperator
{
	public @S(10) Julia_KeywordChoice builtinConstant = new Julia_KeywordChoice("false", "true");
}
