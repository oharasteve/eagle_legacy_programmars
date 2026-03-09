// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class SQL_Literal extends TerminalLiteralToken
{
	public SQL_Literal()
	{
		super("'`\"", true, '\\', true, false);
	}
}
