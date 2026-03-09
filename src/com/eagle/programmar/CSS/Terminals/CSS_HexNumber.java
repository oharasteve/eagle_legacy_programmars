// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 7, 2010

package com.eagle.programmar.CSS.Terminals;

import com.eagle.tokens.terminals.TerminalHexNumberToken;

public class CSS_HexNumber extends TerminalHexNumberToken
{
	public CSS_HexNumber()
	{
		super("#", null, false);
	}
}
