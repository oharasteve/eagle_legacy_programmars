// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Basic_Expression;
import com.eagle.programmar.Basic.Symbols.Basic_Identifier_Definition;
import com.eagle.programmar.Basic.Terminals.Basic_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Basic_DimStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_Keyword DIM = new Basic_Keyword("DIM");
	public @S(20) SeparatedList<Basic_DimEntry,PunctuationComma> values;
	
	public static class Basic_DimEntry extends TokenSequence
	{
		public @S(10) Basic_Identifier_Definition id;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<Basic_Expression,PunctuationComma> dimensions;
		public @S(40) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		throw new RuntimeException("Need to implement");
	}
}
