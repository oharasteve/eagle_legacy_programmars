// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Eaglish_Set_Statement extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword SET = new Eaglish_Keyword("SET");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Eaglish_Expression value;
	public @S(50) Eaglish_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(value);
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(),
				var.getStartChar(), var.getValue(), val);
	}
}
