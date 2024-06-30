// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleString;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Eaglish_String_Data extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) Eaglish_Keyword STRING = new Eaglish_Keyword("STRING");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) @OPT Eaglish_String_InitialValue init;
	public @S(40) Eaglish_EndOfLine eoln;

	public static class Eaglish_String_InitialValue extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Eaglish_Expression value;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (init.isPresent())
		{
			String str = interpreter.getStrValue(init.value);
			EagleString val = new EagleString(str);
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(),
					var.getValue(), val);
		}
	}
}
