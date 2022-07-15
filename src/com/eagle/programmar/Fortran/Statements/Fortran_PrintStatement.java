// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Fortran_PrintStatement extends TokenSequence
{
	public @S(10) Fortran_Keyword PRINT = new Fortran_Keyword("PRINT");
	public @S(20) PunctuationStar star;
	public @S(30) PunctuationComma comma;
	public @S(40) Fortran_Expression expression;
	public @S(50) Fortran_EOLN eoln;
}
