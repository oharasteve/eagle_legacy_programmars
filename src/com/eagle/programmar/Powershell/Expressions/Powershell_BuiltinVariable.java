// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Terminals.Powershell_PunctuationChoice;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_BuiltinVariable extends PrimaryOperator
{
	// Doesn't work to put this up at the top. Ends up with just a
	// Powershell_Punctuation('\0')
	public @S(10) Powershell_PunctuationChoice dollarQuestion = new Powershell_PunctuationChoice("$?", "$_");
}
