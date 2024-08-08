// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 17, 2024

package com.eagle.programmar.AWK.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleHash;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.AWK.AWK_Expression;
import com.eagle.programmar.AWK.AWK_Variable;
import com.eagle.programmar.AWK.AWK_Variable.AWK_VarSubscript;
import com.eagle.programmar.AWK.Terminals.AWK_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class AWK_Assignment extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) AWK_Variable variable;
	public @S(20) AWK_PunctuationChoice equals = new AWK_PunctuationChoice("=", "+=", "-=", "*=", "/=");
	public @S(30) AWK_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue var = interpreter.findSymbol(variable.id.toString());
		EagleValue val = interpreter.getEagleValue(expr);

		if (variable.subscripts != null && variable.subscripts.size() == 1)
		{
			EagleHash hash = (EagleHash) var;
			if (hash == null)
			{
				hash = new EagleHash();
				interpreter.setSymbol(variable.getFileName(), variable.getStartLine(),
						variable.getStartChar(), variable.id.getValue(), hash);
			}
			AWK_VarSubscript sub = variable.subscripts.first();
			String key = interpreter.getStrValue(sub.expr);
			hash.putValue(key, val);
		}
		else
		{
			EagleValue v;
			switch (equals.getValue())
			{
			case "=":
				v = val;
				break;
			case "+=":
				v = new EagleInteger(var.forceIntegerValue() + val.forceIntegerValue());
				break;
			case "-=":
				v = new EagleInteger(var.forceIntegerValue() - val.forceIntegerValue());
				break;
			case "*=":
				v = new EagleInteger(var.forceIntegerValue() * val.forceIntegerValue());
				break;
			case "/=":
				v = new EagleInteger(var.forceIntegerValue() / val.forceIntegerValue());
				break;
			default:
				throw new RuntimeException("Unable to handle " + equals.getValue());
			}

			interpreter.setSymbol(variable.getFileName(), variable.getStartLine(), variable.getStartChar(),
					variable.id.getValue(), v);
		}
	}
}