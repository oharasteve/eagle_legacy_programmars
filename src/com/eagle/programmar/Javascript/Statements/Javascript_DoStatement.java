// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript.Statements;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Javascript_Element;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_DoStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("js_loop_while.asp") Javascript_Keyword DO = new Javascript_Keyword("do");
	public @S(20) Javascript_Element doStatement;
	public @S(30) Javascript_Keyword WHILE = new Javascript_Keyword("while");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Javascript_Expression condition;
	public @S(60) PunctuationRightParen rightParen;
	public @S(70) @OPT PunctuationSemicolon semicolon;
}
