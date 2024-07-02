// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2011

package com.eagle.programmar.Java.Statements;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Statement;
import com.eagle.programmar.Java.Terminals.Java_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_SynchronizedStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @NEWLINE @DOC("statements.html#14.19") Java_Keyword SYNCHRONIZED = new Java_Keyword("synchronized");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @NOSPACE Java_Expression expr;
	public @S(40) @NOSPACE PunctuationRightParen rightParen;
	public @S(50) Java_Statement syncStatement;
}
