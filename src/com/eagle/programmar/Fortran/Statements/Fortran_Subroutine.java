// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_Subroutine extends TokenSequence
{
	public @S(10) @DOC("6j4m0vnbg/index.html") Fortran_Keyword SUBROUTINE1 = new Fortran_Keyword("SUBROUTINE");
	public @S(20) Fortran_Function_Definition fnName1;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) Fortran_EOLN eoln1;

	public @S(70) TokenList<Fortran_Statement> statements;

	public @S(80) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(90) Fortran_Keyword SUBROUTINE2 = new Fortran_Keyword("SUBROUTINE");
	public @S(100) Fortran_Function_Reference fnName2;
	public @S(110) Fortran_EOLN eoln2;
}
