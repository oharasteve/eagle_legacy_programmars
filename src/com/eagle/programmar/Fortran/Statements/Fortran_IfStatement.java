// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Fortran_IfStatement extends TokenSequence
{
	public @S(10) Fortran_Keyword IF1 = new Fortran_Keyword("IF");
	public @S(20) Fortran_Expression condition;
	public @S(30) Fortran_Keyword THEN = new Fortran_Keyword("THEN");
	public @S(40) Fortran_EOLN eoln1;
	public @S(50) TokenList<Fortran_Statement> statements;
	public @S(60) @OPT Fortran_IfElseBlock ifElse;
	public @S(70) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(80) Fortran_Keyword IF2 = new Fortran_Keyword("IF");
	public @S(90) Fortran_EOLN eoln2;
	
	public static class Fortran_IfElseBlock extends TokenSequence
	{
		public @S(10) Fortran_Keyword ELSE = new Fortran_Keyword("ELSE");
		public @S(20) Fortran_EOLN eoln;
		public @S(30) TokenList<Fortran_Statement> statements;
	}
}
