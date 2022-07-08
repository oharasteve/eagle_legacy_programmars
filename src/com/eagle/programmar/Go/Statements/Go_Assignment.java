// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Go_Assignment extends TokenSequence
{
	public @S(10) Go_Variable var;
	public @S(20) Go_Punctuation colonEquals = new Go_Punctuation(":=");
	public @S(30) Go_Expression value;
	public @S(40) Go_EOLN eoln;
}
