// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 24, 2019

package com.eagle.programmar.Rust.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalLiteralToken;

public class Rust_Literal extends TerminalLiteralToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericLiteral(lines, "\"", true, '\\', false, false);
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String txt = _txt;
		if (_txt.startsWith("\""))
		{
			txt = _txt.substring(1, _txt.length()-1);	// Remove quotes
		}
		interpreter.pushStr(txt);
	}
}
