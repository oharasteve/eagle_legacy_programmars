// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Julia.Statements;

import com.eagle.programmar.Julia.Julia_Statement;
import com.eagle.programmar.Julia.Terminals.Julia_EOLN;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Julia_BlockStatement extends TokenSequence
{
	public @S(10) @DOC("control-flow/#man-compound-expressions") PunctuationLeftBrace leftBrace;
	public @S(20) Julia_EOLN eoln1;
	public @S(30) TokenList<Julia_Statement> stmt;
	public @S(40) PunctuationRightBrace rightBrace;
	public @S(50) @OPT Julia_EOLN eoln2;
}
