// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenSequence;

public class Julia_PrintlnStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("base/io-network/#Base.println") Julia_Keyword PRINTLN = new Julia_Keyword("println");
	public @S(20) Julia_Expression expr;
	public @S(30) Julia_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String val = interpreter.getStrValue(expr);
		System.out.println(val);
	}
}
