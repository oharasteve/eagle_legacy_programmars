// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 4, 2024

package com.eagle.programmar.CMD.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CMD.CMD_Expression;
import com.eagle.programmar.CMD.Terminals.CMD_Filename;
import com.eagle.programmar.CMD.Terminals.CMD_PunctuationChoice;
import com.eagle.programmar.CMD.Terminals.CMD_RawArgument;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMD_GenericStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) CMD_Expression programName;
	public @S(20) @OPT TokenList<CMD_GenericArgument> args;

	public static class CMD_GenericArgument extends TokenChooser
	{
		public @CHOICE CMD_PunctuationChoice XXpunct = new CMD_PunctuationChoice(",", "-", "/", "%*");
		public @CHOICE CMD_Filename XXfileName;
		public @LAST CMD_Expression XXexpr;
		public @LAST CMD_RawArgument XXrawArg;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		
	}
}