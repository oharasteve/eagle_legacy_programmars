// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalBinaryNumberToken;

public class Rust_BinaryNumber extends TerminalBinaryNumberToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericBinary(lines, "0b", true);
	}
}
