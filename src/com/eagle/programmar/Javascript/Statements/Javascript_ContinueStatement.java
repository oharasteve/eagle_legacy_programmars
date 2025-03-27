// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ContinueStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Javascript_Keyword CONTINUE = new Javascript_Keyword("continue");
	public @S(20) @OPT PunctuationSemicolon semicolon;
}
