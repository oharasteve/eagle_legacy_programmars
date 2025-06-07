// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

package com.eagle.programmar.RPGFree.Statements;

import com.eagle.programmar.RPGFree.RPGFree_Expression;
import com.eagle.programmar.RPGFree.Terminals.RPGFree_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class RPGFree_Display extends TokenSequence
{
	public @S(10) RPGFree_Keyword DSPLY = new RPGFree_Keyword("dsply");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) RPGFree_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) PunctuationSemicolon semicolon;
}
