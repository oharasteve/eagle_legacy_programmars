// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Comment extends TokenSequence
{
	public @S(10) CMacro_Keyword COMMENT = new CMacro_Keyword("comment");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CMacro_Keyword LIB = new CMacro_Keyword("lib");
	public @S(40) PunctuationComma comma;
	public @S(50) CMacro_Literal literal;
	public @S(60) PunctuationRightParen rightParen;
}
