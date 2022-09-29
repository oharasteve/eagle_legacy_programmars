// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2022

package com.eagle.programmar.Javascript.Symbols;

import com.eagle.parsers.EagleFileReader;

public class Javascript_Field_Definition extends Javascript_Identifier_Definition
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		// Allow reserved words for a field name
		if (! genericIdentifier(lines, ALPHAS+"_", ALPHAS+DIGITS+"_-", false, true)) return false;
		return true;
	}

	@Override
	public DefinitionType getType()
	{
		return DefinitionType.FIELD;
	}
}
