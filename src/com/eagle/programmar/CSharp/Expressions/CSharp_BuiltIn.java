// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_BuiltIn extends PrimaryOperator
{
	public @S(10) CSharp_KeywordChoice builtIn = new CSharp_KeywordChoice(
			"default", "false", "true", "null", "this", "super");
}
