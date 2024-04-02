// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Statements.Powershell_Command;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_CallCommand extends PrimaryOperator
{
	public @S(10) Powershell_Command command;
}
