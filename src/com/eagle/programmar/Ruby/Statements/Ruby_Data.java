// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Symbols.Ruby_Variable_Definition;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Ruby_Data extends TokenSequence implements AbstractStatement
{
	public @S(10) Ruby_Keyword VAR = new Ruby_Keyword("var");
	public @S(20) Ruby_Variable_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Ruby_Expression value;
	public @S(50) Ruby_EOLN eoln;
}
