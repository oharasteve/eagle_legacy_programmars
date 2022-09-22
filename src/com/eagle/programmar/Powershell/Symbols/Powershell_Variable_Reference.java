// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 21, 2022

package com.eagle.programmar.Powershell.Symbols;

import com.eagle.parsers.EagleFileReader;

public class Powershell_Variable_Reference extends Powershell_Identifier_Reference
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		// Allow reserved words because the $ is required for variables
		if (! genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_-", false, true)) return false;
		removeTrailingHyphens();
		return true;
	}
}
