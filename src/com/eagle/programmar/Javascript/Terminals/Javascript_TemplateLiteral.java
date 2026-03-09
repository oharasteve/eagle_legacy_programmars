// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2022

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Javascript_TemplateLiteral extends TerminalLiteralToken
{
	public Javascript_TemplateLiteral()
	{
		super("`", true, '\\', false, true);
	}
}
