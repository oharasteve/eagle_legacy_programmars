// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.IntegerValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Eaglish_Integer_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword INTEGER = new Eaglish_Keyword("INTEGER");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) @OPT Eaglish_Integer_InitialValue init;
	public @S(40) Eaglish_EndOfLine eoln;
	
	public static class Eaglish_Integer_InitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Eaglish_Expression value;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init.isPresent())
		{
			int x = interpreter.getIntValue(init.value);
			IntegerValue val = new IntegerValue(x);
			interpreter._symbolTable.setSymbol(var.getValue(), val);
		}
	}
}
