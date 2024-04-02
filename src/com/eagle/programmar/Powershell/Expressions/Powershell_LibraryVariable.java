// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.programmar.Powershell.Powershell_Library;
import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_LibraryVariable extends PrimaryOperator
{
	public @S(10) Powershell_Library libName;
	public @S(20) Powershell_Identifier_Reference variable;
}
