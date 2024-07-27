// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.CMD;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMD.Statements.CMD_Unparsed_Statement;
import com.eagle.programmar.CMD.Symbols.CMD_Label_Definition;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

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

	public @S(10) @OPT TokenList<CMD_CommandOrLabelOrUnparsed> commands;

	public @SKIP static class CMD_CommandOrLabelOrUnparsed extends TokenChooser
	{
		public @CHOICE CMD_Command XXcommand;
		public @CHOICE CMD_Label XXlabel;
		public @LAST CMD_Unparsed_Statement XXunparsed;
	}

	public @SKIP static class CMD_Label extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) CMD_Label_Definition label;
		public @S(30) CMD_EndOfLine eoln;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (CMD_CommandOrLabelOrUnparsed stmt : commands._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof CMD_Label_Definition)
			{
				CMD_Label_Definition fn = (CMD_Label_Definition) which;
				interpreter._functionList.add(fn);
			}
		}

		// Second pass, execute the program
		for (CMD_CommandOrLabelOrUnparsed stmt : commands._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof CMD_Command)
			{
				CMD_Command cmd = (CMD_Command) which;
				AbstractToken command = cmd.command.getWhich();
				interpreter.tryToInterpret(command);
			}
		}
	}
}
