// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Java_BreakStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.15") Java_Keyword BREAK = new Java_Keyword("break");
	public @S(20) @OPT Java_Identifier_Reference label;
	public @S(30) @NOSPACE PunctuationSemicolon semicolon;
}
