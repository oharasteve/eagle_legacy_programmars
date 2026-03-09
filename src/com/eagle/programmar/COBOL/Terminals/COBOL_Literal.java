// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class COBOL_Literal extends TerminalLiteralToken
{
	public COBOL_Literal()
	{
		super("'\"", false, '?', true, false);
	}
}
