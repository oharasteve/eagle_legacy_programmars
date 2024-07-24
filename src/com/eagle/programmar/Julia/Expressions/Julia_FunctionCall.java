// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Expressions;

import com.eagle.programmar.Julia.Julia_Expression;
import com.eagle.programmar.Julia.Julia_Variable;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Julia_FunctionCall extends PrimaryOperator implements AbstractStatement
{
	public @S(10) Julia_Variable func;
	public @S(20) Julia_FunctionArguments args;

	public static class Julia_FunctionArguments extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<Julia_Expression, PunctuationComma> arguments;
		public @S(30) PunctuationRightParen rightParen;
	}
}
