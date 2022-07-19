// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Go_ConstBlock extends TokenSequence
{
	public @S(10) @DOC("#Constant_declarations") Go_Keyword CONST = new Go_Keyword("const");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Go_EOLN eoln1;
	public @S(40) TokenList<Go_Assignment> constLine;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) Go_EOLN eoln2;
}
