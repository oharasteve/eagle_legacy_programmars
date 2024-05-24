// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Literal;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_RuntimeChecks extends TokenSequence
{
	public @S(10) CMacro_Keyword RUNTIMECHECKS = new CMacro_Keyword("runtime_checks");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CMacro_Literal what;
	public @S(40) @OPT PunctuationComma comma1;
	public @S(50) @OPT CMacro_Keyword OFF = new CMacro_Keyword("off");
	public @S(60) @OPT PunctuationComma comma2;
	public @S(70) @OPT CMacro_Number number; // 1 perhaps
	public @S(80) PunctuationRightParen rightParen;
}