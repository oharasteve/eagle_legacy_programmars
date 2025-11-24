// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 7, 2010

package com.eagle.programmar.CSS.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class CSS_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, null, null, true, false, '?');
	}

	@Override
	public String description()
	{
		return super.genericDescription(null, null, true, false, '?');
	}
}
