// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Fortran_DoStatement extends TokenSequence
{
	public @S(10) @DOC("6j4m0vn8c/index.html") Fortran_Keyword DO1 = new Fortran_Keyword("DO");
	public @S(20) Fortran_Variable_Reference var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Fortran_Expression start;
	public @S(50) PunctuationComma comma;
	public @S(60) Fortran_Expression stop;
	public @S(70) @OPT Fortran_DoIncrement incr;
	public @S(80) Fortran_EOLN eoln1;
	public @S(90) TokenList<Fortran_Statement> statements;
	public @S(100) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(110) Fortran_Keyword DO2 = new Fortran_Keyword("DO");
	public @S(120) Fortran_EOLN eoln2;
	
	public static class Fortran_DoIncrement extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Fortran_Expression incr;
	}
}
