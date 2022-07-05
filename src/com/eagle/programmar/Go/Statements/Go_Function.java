// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Symbols.Go_Function_Definition;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;

public class Go_Function extends TokenSequence
{
	public @S(10) Go_Keyword FUNC = new Go_Keyword("func");
	public @S(20) Go_Function_Definition id;
}
