// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

package com.eagle.programmar.Fortran;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;

public class Fortran_Program extends EagleLanguage implements EagleRunnable
{
	public static final String FORTRAN = "Fortran";

	public Fortran_Program()
	{
		super(FORTRAN, new Fortran_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.oracle.com/cd/E19957-01/805-4939/";
	}

	public @S(10) TokenList<Fortran_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Fortran_Statement elt : statements._elements)
		{
			interpreter.tryToInterpret(elt.getWhich());
		}
	}
}
