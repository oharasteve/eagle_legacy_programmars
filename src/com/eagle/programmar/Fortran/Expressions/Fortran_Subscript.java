// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Fortran.Expressions;

import com.eagle.programmar.Fortran.Fortran_Expression;
import com.eagle.programmar.Fortran.Symbols.Fortran_Identifier_Reference;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Fortran_Subscript extends PrimaryOperator
{
	public @S(10) Fortran_Identifier_Reference variable;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Fortran_Expression,PunctuationColon> args;
	public @S(40) PunctuationRightParen rightParen;
}
