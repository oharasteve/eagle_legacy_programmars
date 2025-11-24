// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2020

package com.eagle.programmar.BNF.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class BNF_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "Lf", true, false, '?');
	}

	@Override
	public String description()
	{
		return super.genericDescription("Ee", "Lf", true, false, '?');
	}
}
