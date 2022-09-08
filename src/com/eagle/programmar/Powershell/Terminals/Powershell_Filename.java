// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

package com.eagle.programmar.Powershell.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalFilename;

public class Powershell_Filename extends TerminalFilename
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericFilename(lines, "Variable"); // Allow Variable: in addition to C:
	}
}
