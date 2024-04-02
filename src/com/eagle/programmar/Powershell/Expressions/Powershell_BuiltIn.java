// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_BuiltIn extends PrimaryOperator
{
	public @S(10) Powershell_KeywordChoice builtin = new Powershell_KeywordChoice(
			"length");
}
