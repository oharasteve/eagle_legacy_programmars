// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_BangOperator extends PrimaryOperator
{
	public @S(10) Powershell_Punctuation BANG = new Powershell_Punctuation("!");
	public @S(20) Powershell_Expression expr;
}
