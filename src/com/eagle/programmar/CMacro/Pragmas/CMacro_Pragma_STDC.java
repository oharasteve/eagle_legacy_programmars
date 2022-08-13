// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.tokens.TokenSequence;

public class CMacro_Pragma_STDC extends TokenSequence
{
	public @S(10) CMacro_Keyword STDC = new CMacro_Keyword("STDC");
	public @S(20) CMacro_Keyword FP_CONTRACT = new CMacro_Keyword("FP_CONTRACT");
	public @S(30) CMacro_Keyword OFF = new CMacro_Keyword("OFF");
}
