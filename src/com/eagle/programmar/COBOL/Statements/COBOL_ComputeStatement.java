// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Subscript;
import com.eagle.programmar.COBOL.Symbols.COBOL_Modifiable_Identifier;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class COBOL_ComputeStatement extends COBOL_AbstractStatement implements EagleRunnable
{
	public @S(10) @DOC("rlpscomp.htm") COBOL_Keyword COMPUTE = new COBOL_Keyword("COMPUTE");
	public @S(20) COBOL_Modifiable_Identifier var;
	public @S(30) @OPT COBOL_Subscript subscript;
	public @S(40) @OPT COBOL_Keyword ROUNDED = new COBOL_Keyword("ROUNDED");
	public @S(50) PunctuationEquals equals;
	public @S(60) COBOL_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.setSymbol(var, var.getValue(), val);
	}
}
