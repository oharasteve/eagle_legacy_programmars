// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Powershell_ReturnStatement extends TokenSequence implements AbstractStatement, EagleRunnableWithResult
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#854-the-return-statement") Powershell_Keyword RETURN = new Powershell_Keyword(
			"Return");
	public @S(20) @OPT Powershell_Expression expr;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		if (expr != null && expr.isPresent())
		{
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.pushEagleValue(val);
		}
		return Eagle_Statement_Result.RETURN;
	}
}
