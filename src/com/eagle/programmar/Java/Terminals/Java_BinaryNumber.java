// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 26, 2019

package com.eagle.programmar.Java.Terminals;

import com.eagle.tokens.terminals.TerminalBinaryNumberToken;

public class Java_BinaryNumber extends TerminalBinaryNumberToken
{
	public Java_BinaryNumber()
	{
		super("0b", true);
	}
}
