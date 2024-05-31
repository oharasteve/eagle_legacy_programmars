// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Variable;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Fortran_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) Fortran_Variable variable;
	public @S(20) PunctuationEquals equals;
	public @S(30) Fortran_Expression expr;
	public @S(40) Fortran_EOLN eoln;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter._symbolTable.setSymbol(variable.getFileName(), variable.getStartLine(), variable.getStartChar(),
				variable.var.toString(), val);
	}
}
