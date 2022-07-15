// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenSequence;

public class Fortran_ExitStatement extends TokenSequence
{
	public @S(10) Fortran_Keyword EXIT = new Fortran_Keyword("EXIT");
	public @S(20) Fortran_EOLN eoln;
}
