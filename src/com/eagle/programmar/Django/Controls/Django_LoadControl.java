// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 16, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Django_LoadControl extends TokenSequence
{
	public @S(10) Django_Keyword LOAD = new Django_Keyword("load");
	public @S(20) TokenList<Django_Variable_Definition> variables;
}
