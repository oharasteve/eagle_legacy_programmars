// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_CommentRestOfLine;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMacro_Pragma_Mark extends TokenSequence
{
	public @S(10) CMacro_Keyword MARK = new CMacro_Keyword("mark");
	public @S(20) @OPT PunctuationHyphen minus;
	public @S(30) @OPT CMacro_CommentRestOfLine what;
}