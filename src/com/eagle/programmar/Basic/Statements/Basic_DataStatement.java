// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

package com.eagle.programmar.Basic.Statements;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Basic.Terminals.Basic_KeywordChoice;
import com.eagle.programmar.Basic.Terminals.Basic_Number;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Basic_DataStatement extends TokenSequence
		implements EagleRunnable, AbstractStatement
{
	public @S(10) Basic_KeywordChoice DATA = new Basic_KeywordChoice("DATA", "DAT");
	public @S(20) SeparatedList<Basic_Number, PunctuationComma> values;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Nothing to do, already done in Basic_Program.java
	}
}
