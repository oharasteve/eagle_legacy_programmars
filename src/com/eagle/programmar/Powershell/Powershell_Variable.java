// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Powershell;

import com.eagle.programmar.Powershell.Symbols.Powershell_Identifier_Reference;
import com.eagle.tokens.TokenChooser;

public class Powershell_Variable extends TokenChooser
{
	public @CHOICE Powershell_Identifier_Reference id;
}
