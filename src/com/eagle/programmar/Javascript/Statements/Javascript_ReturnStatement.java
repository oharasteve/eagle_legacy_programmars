// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_ReturnStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("js_functions.asp") Javascript_Keyword RETURN = new Javascript_Keyword("return");
	public @S(20) @OPT Javascript_Expression expression;
	public @S(30) @OPT PunctuationSemicolon semicolon;
}
