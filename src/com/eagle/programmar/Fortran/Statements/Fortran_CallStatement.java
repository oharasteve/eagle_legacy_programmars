// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_CallStatement extends TokenSequence
{
	public @S(10) Fortran_Keyword CALL = new Fortran_Keyword("CALL");
	public @S(20) Fortran_Function_Reference subroutine;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Fortran_Expression,PunctuationComma> arguments;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) Fortran_EOLN eoln;
}
