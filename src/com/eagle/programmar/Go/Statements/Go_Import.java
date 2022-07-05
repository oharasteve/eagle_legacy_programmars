// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.programmar.Go.Terminals.Go_Literal;
import com.eagle.tokens.TokenSequence;

public class Go_Import extends TokenSequence
{
	public @S(10) Go_Keyword IMPORT = new Go_Keyword("import");
	public @S(20) Go_Literal literal;
	public @S(30) Go_EOLN eoln;
}
