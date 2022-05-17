// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2022

package com.eagle.programmar.IntelASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalHexNumberToken;

public class IntelASM_HexNumber extends TerminalHexNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericHex(lines, "0x", null, false);
	}
}
