// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Expression;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Ruby_FunctionCall extends TokenSequence
{
	public @S(10) Ruby_Variable func;
	public @S(20) @OPT Ruby_FunctionArguments args;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	public @S(40) Ruby_EOLN eoln;
	
	public static class Ruby_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Ruby_Expression,PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}
}
