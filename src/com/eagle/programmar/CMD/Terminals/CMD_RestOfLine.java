// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenRestOfLine;

public class CMD_RestOfLine extends TokenRestOfLine implements EagleRunnable
{
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Cheating, sort of. Need to convert %X% to the value of X
		CMD_RawArgument arg = new CMD_RawArgument();
		arg.setValue(this.getValue());
		arg.interpret(interpreter);
	}
}
