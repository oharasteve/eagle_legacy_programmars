// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

package com.eagle.programmar.Java.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.TerminalBinaryNumberToken;

public class Java_BinaryNumber extends TerminalBinaryNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericBinary(lines, "0b", true);
	}
}
