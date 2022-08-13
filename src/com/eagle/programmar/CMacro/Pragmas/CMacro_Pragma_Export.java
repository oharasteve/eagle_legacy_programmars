// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class CMacro_Pragma_Export extends TokenSequence
{
	public @S(10) CMacro_KeywordChoice EXPORT = new CMacro_KeywordChoice("export", "import");
	public @S(20) CMacro_KeywordChoice ON = new CMacro_KeywordChoice("on", "off");
}
