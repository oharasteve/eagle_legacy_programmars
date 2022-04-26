// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 24, 2022

package com.eagle.programmar.CPlus.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalNumberToken;

public class CPlus_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "LfUu", true, true, '\'');
	}
}
