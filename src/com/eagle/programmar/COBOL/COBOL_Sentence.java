// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.COBOL;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_Sentence extends TokenSequence implements EagleRunnable
{
	public @S(10) TokenList<COBOL_StatementOrComment> statements;
	public @S(20) @OPT PunctuationPeriod dot1;
	public @S(30) @CURIOUS("SENTENCE: Extra dot") @OPT PunctuationPeriod dot2;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (COBOL_StatementOrComment statement : statements._elements)
		{
			interpreter.tryToInterpret(statement);
		}
	}
}