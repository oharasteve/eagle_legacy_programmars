// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2025

package com.eagle.programmar.BNF.Expressions;

import com.eagle.programmar.BNF.BNF_Expression;
import com.eagle.programmar.BNF.Terminals.BNF_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class BNF_Group extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) BNF_Expression expression;
	public @S(30) PunctuationRightParen rightParen;
	public @S(40) @OPT BNF_PunctuationChoice starOrPlus = new BNF_PunctuationChoice("*", "+");
}