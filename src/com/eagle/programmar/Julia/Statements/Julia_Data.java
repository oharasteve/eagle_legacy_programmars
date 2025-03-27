// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Symbols.Julia_Variable_Definition;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.programmar.Julia.Terminals.Julia_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class Julia_Data extends TokenSequence implements AbstractStatement
{
	public @S(10) Julia_Keyword VAR = new Julia_Keyword("var");
	public @S(20) Julia_Variable_Definition id;
	public @S(30) PunctuationEquals equals;
	public @S(40) Julia_Expression value;
	public @S(50) Julia_EOLN eoln;
}
