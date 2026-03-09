// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 17, 2022

package com.eagle.programmar.Python.Terminals;

import com.eagle.tokens.terminals.TerminalBinaryNumberToken;

public class Python_BinaryNumber extends TerminalBinaryNumberToken
{
	public Python_BinaryNumber()
	{
		super("0b", false);
	}
}
