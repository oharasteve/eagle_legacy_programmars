// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_Expression;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_IfFunction extends TokenSequence implements AbstractStatement
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @DOC("s_if.htm") Lisp_Keyword IF = new Lisp_Keyword("if");
	public @S(30) Lisp_Expression condition;
	public @S(40) Lisp_Expression ifTrue;
	public @S(50) @OPT Lisp_Expression ifFalse;
	public @S(60) PunctuationRightParen rightParen;
}
