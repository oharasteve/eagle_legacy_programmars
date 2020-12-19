// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Statement;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_WhileStatement extends TokenSequence
{
	public @S(10) @NEWLINE @DOC("js_loop_while.asp") Javascript_Keyword WHILE = new Javascript_Keyword("while");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Javascript_Expression condition;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) Javascript_Statement whileStatement;
}
