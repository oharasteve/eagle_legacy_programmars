// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2014

package com.eagle.programmar.CMD.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CMD.CMD_Argument;
import com.eagle.programmar.CMD.Terminals.CMD_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMD_Exit_Statement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("exit.mspx") CMD_Keyword EXIT = new CMD_Keyword("exit");
	public @S(20) @OPT TokenList<CMD_Exit_Option> opts;
	public @S(30) @OPT CMD_Argument exitValue;

	public static class CMD_Exit_Option extends TokenChooser
	{
		public @CHOICE static class CMD_Exit_Option_B extends TokenSequence
		{
			public @S(10) PunctuationSlash slash;
			public @S(20) CMD_Keyword B = new CMD_Keyword("b");
		}
	}

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(exitValue);
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
}
