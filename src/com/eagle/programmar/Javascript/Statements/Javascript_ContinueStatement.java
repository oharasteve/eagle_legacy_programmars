// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult.Eagle_Statement_Result;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.tokens.AbstractToken;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Runtime.JsValues;
import com.eagle.programmar.Javascript.Runtime.JsRuntime;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ContinueStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) Javascript_Keyword CONTINUE = new Javascript_Keyword("continue");
	public @S(20) @OPT PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		return Eagle_Statement_Result.CONTINUE;
	}
}
