// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 12, 2011

package com.eagle.programmar.Gupta.Statements;

import com.eagle.programmar.Gupta.Gupta_Expression;
import com.eagle.programmar.Gupta.Symbols.Gupta_Identifier_Reference;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Gupta_Set_Statement extends TokenSequence
{
	public @S(10) Gupta_Keyword Set = new Gupta_Keyword("Set");
	public @S(20) Gupta_Identifier_Reference var;
	public @S(30) PunctuationEquals equals;
	public @S(40) Gupta_Expression expr;
}
