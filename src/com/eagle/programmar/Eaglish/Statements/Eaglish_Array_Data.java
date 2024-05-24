// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.ArrayValue;
import com.eagle.math.EagleValue;
import com.eagle.math.StringValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Variable_Definition;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Eaglish_Array_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword ARRAY = new Eaglish_Keyword("ARRAY");
	public @S(20) Eaglish_Variable_Definition var;
	public @S(30) @OPT Eaglish_Array_InitialValues init;
	public @S(40) Eaglish_EndOfLine eoln;

	public static class Eaglish_Array_InitialValues extends TokenSequence
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) SeparatedList<Eaglish_Expression, PunctuationComma> values;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArrayList<EagleValue> vals = null;
		if (init.isPresent())
		{
			vals = new ArrayList<EagleValue>();
			for (int i = 0; i < init.values.getPrimaryCount(); i++)
			{
				Eaglish_Expression expr = init.values.getPrimaryElement(i);
				String val = interpreter.getStrValue(expr);
				vals.add(new StringValue(val));
				if (interpreter._TRACE) System.err.println("*** " + var.toString() + "[" + i + "] = " + val);
			}
		}

		ArrayValue array = new ArrayValue();
		array.setValue(vals);
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), var.toString(),
				array);
	}
}
