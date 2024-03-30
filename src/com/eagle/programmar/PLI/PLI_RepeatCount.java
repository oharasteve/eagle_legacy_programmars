// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 29, 2024

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class PLI_RepeatCount extends TokenSequence
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) PLI_Number count;
	public @S(30) PunctuationRightParen rightParen;
}