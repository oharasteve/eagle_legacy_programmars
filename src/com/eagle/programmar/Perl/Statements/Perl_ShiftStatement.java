// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.tokens.TokenSequence;

public class Perl_ShiftStatement extends TokenSequence
{
	public @S(10) Perl_Keyword SHIFT = new Perl_Keyword("shift");
	public @S(20) Perl_Variable var;
}
