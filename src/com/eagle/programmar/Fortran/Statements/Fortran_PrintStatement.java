// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Fortran_PrintStatement extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("6j4m0vnap/index.html") Fortran_Keyword PRINT = new Fortran_Keyword("PRINT");
	public @S(20) Fortran_PrintFormat format;
	public @S(30) PunctuationComma comma;
	public @S(40) Fortran_Expression expression;
	public @S(50) Fortran_EOLN eoln;

	public static class Fortran_PrintFormat extends TokenChooser
	{
		public @CHOICE PunctuationStar star;
		public @CHOICE Fortran_Literal fmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue result = interpreter.getEagleValue(expression);
		System.out.println(result.toString());
	}
}
