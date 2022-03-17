// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalBinaryNumberToken;

public class Python_BinaryNumber extends TerminalBinaryNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericBinary(lines, "0b");
	}
}
