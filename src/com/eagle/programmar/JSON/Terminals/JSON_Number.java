// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class JSON_Number extends TerminalNumberToken
{
	public JSON_Number()
	{
		super("e", null, true, false, '?');
	}
}
