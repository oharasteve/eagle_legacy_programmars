// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Symbols.Fortran_Common_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class Fortran_Common extends TokenSequence
{
	public @S(10) @DOC("6j4m0vn7v/index.html") Fortran_Keyword COMMON = new Fortran_Keyword("COMMON");
	public @S(20) PunctuationSlash slash1;
	public @S(30) Fortran_Common_Reference common;
	public @S(40) PunctuationSlash slash2;
	public @S(50) SeparatedList<Fortran_Variable_Reference, PunctuationComma> variables;
	public @S(60) Fortran_EOLN eoln;
}
