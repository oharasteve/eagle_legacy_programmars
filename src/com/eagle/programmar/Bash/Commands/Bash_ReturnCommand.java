// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Commands;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_ReturnCommand extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) @DOC("#index-return") Bash_Keyword RETURN = new Bash_Keyword("return");
	public @S(20) @OPT Bash_Expression expr;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (interpreter._currentFunction != null)
		{
			int code = interpreter.getIntValue(expr);
			Bash_Function_Explicit func = (Bash_Function_Explicit) interpreter._currentFunction;
			func._exitStatus = code;
		}
		return Eagle_Statement_Result.RETURN;
	}
}
