// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Type;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Definition;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Fortran_Data extends TokenSequence implements EagleRunnable
{
	public @S(10) Fortran_Type type;
	public @S(20) Fortran_Punctuation colobColon = new Fortran_Punctuation("::");
	public @S(30) SeparatedList<Fortran_Variable_Definition, PunctuationComma> variables;
	public @S(40) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Could create an empty variable here
	}
}
