// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.terminals.TerminalNumberToken;

public class HTML_Number extends TerminalNumberToken
{
	public HTML_Number()
	{
		super(null, "%", true, false, '?');
	}
}
