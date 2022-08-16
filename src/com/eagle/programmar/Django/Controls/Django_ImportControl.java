// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.tokens.TokenSequence;

public class Django_ImportControl extends TokenSequence
{
	public @S(10) Django_Keyword IMPORT = new Django_Keyword("import");
	public @S(20) Django_Expression module;
	public @S(30) Django_Keyword AS = new Django_Keyword("as");
	public @S(40) Django_Variable_Definition var;
}
