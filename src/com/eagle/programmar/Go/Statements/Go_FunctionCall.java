// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Go_FunctionCall extends TokenSequence
{
	public @S(10) Go_Variable func;
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) SeparatedList<Go_Expression,PunctuationComma> arguments;
	public @S(40) PunctuationRightParen rightParen;
	public @S(50) @OPT PunctuationSemicolon semicolon;
	public @S(60) Go_EOLN eoln;
}
