// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.terminals.TerminalNumberToken;

public class CMacro_Number extends TerminalNumberToken implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		return genericNumber(lines, "Ee", "Lf", true);
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		try
		{
			int x = Integer.parseInt(_numberAsText);
			interpreter.pushInt(x);
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable to evaluate " + _numberAsText);
		}
	}
}
