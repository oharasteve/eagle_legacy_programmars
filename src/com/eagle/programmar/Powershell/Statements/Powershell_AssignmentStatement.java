// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Powershell.Statements;

import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.tokens.TokenSequence;

public class Powershell_AssignmentStatement extends TokenSequence
{
	public @S(10) Powershell_Expression expr;
}
