// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Format;
import com.eagle.tokens.TokenRestOfLine;

public class CMD_RestOfLine extends TokenRestOfLine implements EagleRunnable
{
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = this.getValue();
		String formatted = CMD_Format.format(interpreter, val);
		interpreter.pushStr(formatted);
	}
}
