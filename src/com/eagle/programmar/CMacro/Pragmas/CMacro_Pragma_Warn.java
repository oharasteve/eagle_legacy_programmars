// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMacro_Pragma_Warn extends TokenSequence
{
	public @S(10) CMacro_Keyword WARN = new CMacro_Keyword("warn");
	public @S(20) @OPT PunctuationHyphen minus;
	public @S(30) CMacro_Number what;	// 8004 8008 8066 perhaps
}
