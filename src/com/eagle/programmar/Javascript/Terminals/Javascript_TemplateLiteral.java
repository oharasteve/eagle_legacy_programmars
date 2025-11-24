// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2022

package com.eagle.programmar.Javascript.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Javascript_TemplateLiteral extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		// backticks can span multiple lines, and can inject values with $(x) inside
		return genericLiteral(lines, "`", true, '\\', false, true);
	}

	@Override
	public String description()
	{
		return super.genericDescription("`", true, '\\', false, true);
	}
}
