// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenList;

public class Gupta_Internal_Functions extends Gupta_Declaration
{
	public @S(10) Gupta_Keyword Internal = new Gupta_Keyword("Internal");
	public @S(20) Gupta_Keyword Functions = new Gupta_Keyword("Functions");
	public @S(30) @OPT TokenList<Gupta_Function> functions;
}
