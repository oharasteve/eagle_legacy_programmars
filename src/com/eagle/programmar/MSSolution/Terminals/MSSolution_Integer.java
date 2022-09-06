// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

package com.eagle.programmar.MSSolution.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalIntegerToken;

public class MSSolution_Integer extends TerminalIntegerToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericInteger(lines);
	}
}