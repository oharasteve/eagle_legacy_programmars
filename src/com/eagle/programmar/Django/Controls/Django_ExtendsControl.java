// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.tokens.TokenSequence;

public class Django_ExtendsControl extends TokenSequence
{
	public @S(10) Django_Keyword EXTENDS = new Django_Keyword("extends");
	public @S(20) Django_Literal literal;
}
