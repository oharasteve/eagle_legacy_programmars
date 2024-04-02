// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;

public class Powershell_AssignmentExpression extends PrecedenceOperator
{
	public @S(10) Powershell_Expression var = new Powershell_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) Powershell_PunctuationChoice equals = new Powershell_PunctuationChoice(
			"=",
			"*=",
			"/=",
			"%=",
			"+=",
			"-=");
	public @S(30) Powershell_Expression expr = new Powershell_Expression(this, AllowedPrecedence.ATLEAST);
}
