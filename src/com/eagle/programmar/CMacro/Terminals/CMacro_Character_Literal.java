// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class CMacro_Character_Literal extends TerminalLiteralToken
{
	public CMacro_Character_Literal()
	{
		super("'", true, '\\', false, false);
	}
}