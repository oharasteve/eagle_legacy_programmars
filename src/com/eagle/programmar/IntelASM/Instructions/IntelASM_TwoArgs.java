// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2024

package com.eagle.programmar.IntelASM.Instructions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.IntelASM.IntelASM_Expression;
import com.eagle.programmar.IntelASM.IntelASM_Variable;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class IntelASM_TwoArgs extends TokenSequence implements EagleRunnable
{
	public @S(10) IntelASM_KeywordChoice CMD = new IntelASM_KeywordChoice(
			"ADD", "AND", "LEA", "MOV", "MOVSX", "MOVZX",
			"OR", "SHL", "SHR", "SUB", "TEST", "XOR");
	public @S(20) IntelASM_Variable var;
	public @S(30) @NOSPACE PunctuationComma comma;
	public @S(40) IntelASM_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue ev = interpreter.getEagleValue(expr);
		int val;
		if (ev.isInteger())
		{
			val = ev.forceIntegerValue();
		}
		else
		{
			// Convert "0" to 48 (hex 32, octal 60)
			String s = ev.forceStringValue();
			if (s.length() != 1)
			{
				throw new RuntimeException("Characters must be length 1, not " + s);
			}
			val = s.charAt(0);
		}

		switch (CMD.toString().toUpperCase())
		{
		case "ADD":
			int oldVal3 = var.getValue(interpreter);
			var.setValue(interpreter, oldVal3 + val);
			break;
		case "MOV":
			var.setValue(interpreter, val);
			break;
		case "SUB":
			int oldVal2 = var.getValue(interpreter);
			var.setValue(interpreter, oldVal2 - val);
			break;
		case "XOR":
			int oldVal4 = var.getValue(interpreter);
			var.setValue(interpreter, oldVal4 ^ val);
			break;
		default:
			throw new RuntimeException("Unable to run command: " + CMD);
		}
	}
}