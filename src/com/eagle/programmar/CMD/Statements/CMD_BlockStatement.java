// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2024

package com.eagle.programmar.CMD.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.programmar.CMD.CMD_Command;
import com.eagle.programmar.CMD.CMD_Label;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMD_BlockStatement extends TokenSequence implements EagleRunnableWithResult
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @OPT CMD_EndOfLine eoln;
	public @S(30) TokenList<CMD_LabelOrCommand> commands;
	public @S(40) PunctuationRightParen rightParen;

	public static class CMD_LabelOrCommand extends TokenChooser
	{
		public @CHOICE CMD_Command XXcommand;
		public @CHOICE CMD_Label XXlabel;
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
		for (CMD_LabelOrCommand labelOrCmd : commands._elements)
		{
			if (labelOrCmd.getWhich() instanceof CMD_Command)
			{
				CMD_Command cmd = (CMD_Command) labelOrCmd.getWhich();
				result = interpreter.tryToInterpret(cmd.command.getWhich());
				if (result != Eagle_Statement_Result.NORMAL) break;
			}
		}
		return result;
	}
}
