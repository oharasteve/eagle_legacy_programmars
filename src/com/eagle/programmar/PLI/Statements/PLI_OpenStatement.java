// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 3, 2011

package com.eagle.programmar.PLI.Statements;

import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class PLI_OpenStatement extends TokenSequence
{
	public @S(10) @DOC("7.37") PLI_Keyword OPEN = new PLI_Keyword("OPEN");
	public @S(20) PLI_Keyword FILE = new PLI_Keyword("FILE");
	public @S(30) PunctuationLeftParen leftParen1;
	public @S(40) PLI_Identifier_Reference fileName;
	public @S(50) PunctuationRightParen rightParen1;
	public @S(60) PLI_Keyword TITLE = new PLI_Keyword("TITLE");
	public @S(70) PunctuationLeftParen leftParen2;
	public @S(80) PLI_Literal title;
	public @S(90) PunctuationRightParen rightParen2;
	public @S(100) PunctuationSemicolon semicolon;
}
