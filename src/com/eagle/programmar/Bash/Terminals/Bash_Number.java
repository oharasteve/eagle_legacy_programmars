// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class Bash_Number extends TerminalNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, null, null, false, false, '?');
	}

	@Override
	public String description()
	{
		return super.genericDescription(null, null, false, false, '?');
	}
}
