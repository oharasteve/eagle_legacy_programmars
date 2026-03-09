// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Scala.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Scala_Literal extends TerminalLiteralToken
{
	public Scala_Literal()
	{
		super("\"", true, '\\', false, false);
	}
}
