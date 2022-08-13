// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Pack extends TokenSequence
{
	public @S(10) CMacro_Keyword PACK = new CMacro_Keyword("pack");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) @OPT CMacro_KeywordChoice PUSH = new CMacro_KeywordChoice("push", "pop");
	public @S(40) @OPT PunctuationComma comma;
	public @S(50) @OPT CMacro_Number number;	// 1 perhaps
	public @S(60) PunctuationRightParen rightParen;
}