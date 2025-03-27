// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 21, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Type;
import com.eagle.programmar.Go.Symbols.Go_Variable_Definition;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Go_VarStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Go_Keyword VAR = new Go_Keyword("var");
	public @S(20) Go_Variable_Definition variable;
	public @S(30) Go_Type type;
	public @S(40) Go_EOLN eoln;
}
