// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Statements;

import com.eagle.programmar.RPGFree.RPGFree_Expression;
import com.eagle.programmar.RPGFree.Symbols.RPGFree_Identifier_Reference;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;

public class RPGFree_Assignment extends TokenSequence
{
	public @S(10) @OPT PunctuationStar star;
	public @S(20) RPGFree_Identifier_Reference var;
	public @S(30) PunctuationEquals equals;
	public @S(40) RPGFree_Expression value;
	public @S(50) PunctuationSemicolon semicolon;
}
