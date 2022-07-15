// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Fortran_ProgramBlock extends TokenSequence
{
	public @S(10) Fortran_Keyword PROGRAM1 = new Fortran_Keyword("PROGRAM");
	public @S(20) Fortran_Function_Definition fnName1;
	public @S(60) Fortran_EOLN eoln1;
	
	public @S(70) TokenList<Fortran_Statement> statements;
	
	public @S(80) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(90) Fortran_Keyword PROGRAM2 = new Fortran_Keyword("PROGRAM");
	public @S(100) Fortran_Function_Reference fnName2;
	public @S(110) Fortran_EOLN eoln2;
}
