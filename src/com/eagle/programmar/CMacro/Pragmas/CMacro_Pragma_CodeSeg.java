// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2024

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_CodeSeg extends TokenSequence
{
	public @S(10) CMacro_Keyword CODESEG = new CMacro_Keyword("code_seg");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CMacro_Literal literal;
	public @S(40) PunctuationRightParen rightParen;
}
