// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Julia_FunctionCall extends TokenSequence
{
	public @S(10) Julia_Variable func;
	public @S(20) @OPT Julia_FunctionArguments args;
	public @S(30) @OPT PunctuationSemicolon semicolon;
	public @S(40) Julia_EOLN eoln;

	public static class Julia_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Julia_Expression, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}
}
