// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 4, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class AWK_ReturnStatement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) AWK_Keyword RETURN = new AWK_Keyword("RETURN");
	public @S(20) @OPT AWK_Expression expr;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
		return Eagle_Statement_Result.RETURN;
	}
}
