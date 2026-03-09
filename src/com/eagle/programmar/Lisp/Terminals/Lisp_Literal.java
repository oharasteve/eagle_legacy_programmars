// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

package com.eagle.programmar.Lisp.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Lisp_Literal extends TerminalLiteralToken
{
	public Lisp_Literal()
	{
		super("\"", true, '\\', false, true);
	}
}
