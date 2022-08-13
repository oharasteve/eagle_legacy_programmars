// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_CommentRestOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.tokens.TokenSequence;

public class CMacro_Pragma_Region extends TokenSequence
{
	public @S(10) CMacro_Keyword REGION = new CMacro_Keyword("region");
	public @S(20) @OPT CMacro_CommentRestOfLine what;
}
