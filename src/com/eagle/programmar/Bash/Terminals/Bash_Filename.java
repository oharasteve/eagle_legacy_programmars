// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Bash.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalFilename;

public class Bash_Filename extends TerminalFilename
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericFilename(lines);
	}
}
