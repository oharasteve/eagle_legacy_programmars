// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_ArgumentList;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_SuperStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) Java_Keyword SUPER = new Java_Keyword("super");
	public @S(20) @NOSPACE PunctuationLeftParen leftParen;
	public @S(30) @OPT @NOSPACE Java_ArgumentList args;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) @NOSPACE PunctuationSemicolon semicolon;
}
