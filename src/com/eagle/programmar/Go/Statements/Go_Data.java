// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Type;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Go_Data extends TokenSequence implements AbstractStatement, EagleRunnable
{
	public @S(10) @DOC("#Variables") Go_Keyword VAR = new Go_Keyword("var");
	public @S(20) Go_Variable_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Go_Type type;
	public @S(50) Go_Expression initValue;
	public @S(60) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(initValue);
		interpreter.setSymbol(id, id.getValue(), val);
	}
}
