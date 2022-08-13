// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.TokenSequence;

public class CMacro_Pragma_Unroll extends TokenSequence
{
	public @S(10) CMacro_Keyword UNROLL = new CMacro_Keyword("unroll");
	public @S(20) CMacro_Number what;	// 1 perhaps
}