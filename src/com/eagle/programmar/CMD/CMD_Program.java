// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.CMD;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.core.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.metrics.CallMetrics;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class CMD_Program extends EagleLanguage implements EagleRunnable
{
	public static final String CMD = "CMD";

	public CMD_Program()
	{
		super(CMD, new CMD_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.microsoft.com/resources/documentation/windows/xp/all/proddocs/en-us/";
	}

	public @S(10) @OPT TokenList<CMD_CommandOrLabel> commands;

	public @SKIP static class CMD_CommandOrLabel extends TokenChooser
	{
		public @CHOICE CMD_Command XXcommand;
		public @CHOICE CMD_Label XXlabel;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (CMD_CommandOrLabel stmt : commands._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof CMD_Label)
			{
				CMD_Label lbl = (CMD_Label) which;
				interpreter._functionList.put(lbl.label.getValue(), lbl);
				if (lbl._metrics == null)
				{
					lbl._metrics = new CallMetrics(interpreter._metrics, lbl.label.getValue(),
							lbl.getFileName(), lbl.getStartLine(), lbl.getStartChar());
				}
			}
		}

		// Second pass, execute the program
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (CMD_CommandOrLabel stmt : commands._elements)
		{
			if (stmt.getWhich() instanceof CMD_Command)
			{
				result = interpreter.tryToInterpret(stmt.getWhich());
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}
	}
}
