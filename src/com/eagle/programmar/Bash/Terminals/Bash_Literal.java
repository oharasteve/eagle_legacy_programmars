// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.Bash.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Bash_Literal extends TerminalLiteralToken
{
	public Bash_Literal()
	{
		super("'\"`", true, '\\', false, false);
	}
}
