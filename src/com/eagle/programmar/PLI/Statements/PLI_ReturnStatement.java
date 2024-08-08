// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 19, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.PLI.PLI_Expression;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_ReturnStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("7.48") PLI_Keyword RETURN = new PLI_Keyword("RETURN");
	public @S(20) @OPT PLI_Expression expr;
	public @S(30) PunctuationSemicolon semicolon;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
}
