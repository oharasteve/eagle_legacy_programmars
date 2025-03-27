// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Statements.Powershell_Command;
import com.eagle.programmar.Powershell.Terminals.Powershell_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_AmpersandOperator extends PrimaryOperator
{
	public @S(10) Powershell_Punctuation AMPERSAND = new Powershell_Punctuation("&");
	public @S(20) Powershell_Command command;
}
