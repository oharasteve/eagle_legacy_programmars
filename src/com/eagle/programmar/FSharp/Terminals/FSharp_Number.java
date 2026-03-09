// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class FSharp_Number extends TerminalNumberToken
{
	public FSharp_Number()
	{
		// J is for complex ...
		super("Ee", "JjLl", true, false, '?');
	}
}
