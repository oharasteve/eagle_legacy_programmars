// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Fortran_ProgramBlock extends TokenSequence implements EagleRunnable
{
	public @S(10) @DOC("6j4m0vnar/index.html") Fortran_Keyword PROGRAM1 = new Fortran_Keyword("PROGRAM");
	public @S(20) Fortran_Function_Definition fnName1;
	public @S(30) Fortran_EOLN eoln1;

	public @S(40) TokenList<Fortran_Statement> statements;

	public @S(50) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(60) Fortran_Keyword PROGRAM2 = new Fortran_Keyword("PROGRAM");
	public @S(70) Fortran_Function_Reference fnName2;
	public @S(80) Fortran_EOLN eoln2;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Fortran_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
