// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Django_SetControl extends TokenSequence
{
	public @S(10) Django_Keyword SET = new Django_Keyword("set");
	public @S(20) Django_Variable var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Django_Expression expr;
}
