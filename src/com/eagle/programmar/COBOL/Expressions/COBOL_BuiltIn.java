// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class COBOL_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) COBOL_KeywordChoice logicalConstant = new COBOL_KeywordChoice("ANY", "FALSE", "HIGH-VALUES",
			"LINAGE-COUNTER", "LOW-VALUES", "QUOTE", "RETURN-CODE", "SPACE", "SPACES", "TRUE", "ZERO", "ZEROES",
			"ZEROS");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String name = logicalConstant.toString();
		switch (name)
		{
		case "FALSE":
			interpreter.pushBool(false);
			break;
		case "TRUE":
			interpreter.pushBool(true);
			break;
		case "SPACES":
			interpreter.pushStr("");
			break;
		default:
			throw new RuntimeException("Can't handle BuiltIn's other than TRUE/FALSE: " + name);
		}
	}
}
