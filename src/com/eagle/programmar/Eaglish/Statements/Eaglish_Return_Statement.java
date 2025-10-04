// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnableWithResult;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Eaglish_Return_Statement extends TokenSequence implements EagleRunnableWithResult, AbstractStatement
{
	public @S(10) Eaglish_Keyword RETURN = new Eaglish_Keyword("RETURN");
	public @S(20) Eaglish_Expression expr;
	public @S(30) Eaglish_EndOfLine eoln;

	@Override
	public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);

		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof Eaglish_Function)
			{
				Eaglish_Function func = (Eaglish_Function) parent;
				func._returnMetrics.returned(val.typeName());
				break;
			}
			parent = parent.getParent();
		}

		return Eagle_Statement_Result.RETURN;
	}
}
