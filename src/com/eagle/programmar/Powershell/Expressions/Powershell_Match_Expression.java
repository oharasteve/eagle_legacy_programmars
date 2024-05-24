// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_Match_Expression extends PrecedenceOperator
{
	public @S(10) Powershell_Expression left = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Powershell_KeywordChoice operator = new Powershell_KeywordChoice("-cmatch", "-contains", "-f", "-in",
			"-join", "-match", "-notin", "-notlike", "-notmatch", "-replace", "-split");
	public @S(30) Powershell_Expression right = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
}
