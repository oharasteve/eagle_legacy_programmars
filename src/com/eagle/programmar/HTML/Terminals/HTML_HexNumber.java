// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class HTML_HexNumber extends TerminalHexNumberToken
{
	public HTML_HexNumber()
	{
		super("#", null, false);
	}
}
