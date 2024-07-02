// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Ruby_BlockStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) Ruby_EOLN eoln1;
	public @S(30) TokenList<Ruby_Statement> stmt;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Ruby_EOLN eoln2;
}
