// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Statements;

import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class RPGFree_Return extends TokenSequence
{
	public @S(10) RPGFree_Keyword RETURN = new RPGFree_Keyword("return");
	public @S(20) PunctuationSemicolon semicolon;
}
