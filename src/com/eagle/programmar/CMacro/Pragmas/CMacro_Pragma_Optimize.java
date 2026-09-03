// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 3, 2026

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Optimize extends TokenSequence
{
	public @S(10) CMacro_Keyword OPTIMIZE = new CMacro_Keyword("optimize");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CMacro_Literal lit;
	public @S(40) PunctuationComma comma;
	public @S(50) CMacro_KeywordChoice OFF = new CMacro_KeywordChoice("off");
	public @S(60) PunctuationRightParen rightParen;
}

