// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.CMD.CMD_Label;
import com.eagle.programmar.CMD.CMD_Program;
import com.eagle.programmar.CMD.CMD_Program.CMD_CommandOrLabel;
import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;

public class CMD_Goto_Statement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("goto.mspx") CMD_Keyword GOTO = new CMD_Keyword("goto");
	public @S(20) @OPT PunctuationColon colon;
	public @S(30) CMD_Goto_What gotoWhat;

	public static class CMD_Goto_What extends TokenChooser
	{
		public @CHOICE CMD_Identifier_Reference XXlabel;
		public @CHOICE CMD_Keyword XXEOF = new CMD_Keyword("eof");
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (gotoWhat.getWhich() instanceof CMD_Keyword)
		{
			// GOTO :EOF
			CMD_Label func = (CMD_Label) interpreter._currentFunction;
			func._exitStatus = 0;	// Meaning Success
			return Eagle_Statement_Result.RETURN;
		}
		
		CMD_Identifier_Reference label = (CMD_Identifier_Reference) gotoWhat.getWhich();
		// Look it up
		String name = label.getValue();
		CMD_Label func = null;
		for (AbstractFunction fn : interpreter._functionList)
		{
			CMD_Label lblDef = (CMD_Label) fn;
			if (lblDef.label.getValue().equals(name))
			{
				func = lblDef;
				break;
			}
		}
		if (func == null)
		{
			throw new RuntimeException("Unable to find a label named " + name);
		}

		// Transfer control to the label
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		CMD_Program pgm = (CMD_Program) interpreter._lang;
		boolean foundLabel = false;
		for (CMD_CommandOrLabel cmdOr : pgm.commands._elements)
		{
			if (foundLabel)
			{
				// Run the next statement in the program
				result = interpreter.tryToInterpret(cmdOr);
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
			else   // Have to search for our label (aka function)
			{
				if (cmdOr.getWhich() instanceof CMD_Label)
				{
					CMD_Label lbl = (CMD_Label) cmdOr.getWhich();
					if (lbl == func)    // Careful, comparing Objects here
					{
						foundLabel = true;
					}
				}
			}
		}
		if (!foundLabel)
		{
			throw new RuntimeException("Unable to re-find label " + name);
		}
		return result;
	}
}
