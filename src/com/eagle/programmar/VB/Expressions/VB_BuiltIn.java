// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class VB_BuiltIn extends PrimaryOperator
{
	public @S(10) VB_KeywordChoice builtIn = new VB_KeywordChoice("false", "true", "nothing");
}
