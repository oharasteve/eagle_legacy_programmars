// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2026

package com.eagle.programmar.Fortran.Statements;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_SelectStatement extends TokenSequence
{
	public @S(10) Fortran_Keyword SELECT = new Fortran_Keyword("SELECT");
	public @S(20) Fortran_Keyword CASE1 = new Fortran_Keyword("CASE");
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) Fortran_Expression value;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) Fortran_EOLN eoln1;
	public @S(70) TokenList<Fortran_SelectCase> cases;
	public @S(80) @OPT Fortran_SelectDefault defualtCase; 
	public @S(90) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(100) Fortran_Keyword CASE2 = new Fortran_Keyword("CASE");
	public @S(110) Fortran_EOLN eoln2;
	
	public static class Fortran_SelectCase extends TokenSequence
	{
		public @S(10) Fortran_Keyword CASE = new Fortran_Keyword("CASE");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) Fortran_Expression value;
		public @S(40) PunctuationRightParen rightParen;
		public @S(50) Fortran_EOLN eoln;
		public @S(60) TokenList<Fortran_Statement> caseStatements;
	}

	public static class Fortran_SelectDefault extends TokenSequence
	{
		public @S(20) Fortran_Keyword CASE = new Fortran_Keyword("CASE");
		public @S(20) Fortran_Keyword DEFAULT = new Fortran_Keyword("DEFAULT");
		public @S(30) Fortran_EOLN eoln;
		public @S(40) TokenList<Fortran_Statement> defaultStatements;
	}
}
