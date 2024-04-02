// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.FSharp.Expressions;

import com.eagle.programmar.FSharp.Terminals.FSharp_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class FSharp_BuiltIn extends PrimaryOperator
{
	public @S(10) FSharp_KeywordChoice builtins = new FSharp_KeywordChoice("False", "True");
}
