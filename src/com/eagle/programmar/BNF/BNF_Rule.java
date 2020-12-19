// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.BNF;

import com.eagle.programmar.BNF.Symbols.BNF_Rule_Definition;
import com.eagle.programmar.BNF.Terminals.BNF_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class BNF_Rule extends TokenSequence
{
	public @S(10) BNF_Rule_Definition definition;
	public @S(20) BNF_Punctuation coloncolonEquals = new BNF_Punctuation("::=");
	public @S(30) BNF_Expression expression;
	public @S(40) PunctuationSemicolon semicolon;
}
