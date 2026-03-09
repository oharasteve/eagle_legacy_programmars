// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class JavaP_HexNumber extends TerminalHexNumberToken
{
	public JavaP_HexNumber()
	{
		super("0x", "", false);
	}

	// Probably should go into its own class file
	public static class JavaP_HexNoPrefix extends TerminalHexNumberToken
	{
		public JavaP_HexNoPrefix()
		{
			super("", "", false);
		}
	}
}
