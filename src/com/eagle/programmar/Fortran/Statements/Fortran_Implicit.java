// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Fortran_Implicit extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @DOC("6j4m0vn9v/index.html") Fortran_Keyword IMPLICIT = new Fortran_Keyword("IMPLICIT");
	public @S(20) Fortran_Keyword NONE = new Fortran_Keyword("NONE");
	public @S(30) Fortran_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do here
	}

	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		return null; // Nothing to do here
	}
}
