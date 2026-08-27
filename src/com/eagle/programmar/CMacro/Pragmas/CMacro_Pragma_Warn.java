// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 13, 2022

package com.eagle.programmar.CMacro.Pragmas;

import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.programmar.CMacro.Terminals.CMacro_KeywordChoice;
import com.eagle.programmar.CMacro.Terminals.CMacro_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class CMacro_Pragma_Warn extends TokenSequence
{
	public @S(10) CMacro_Keyword WARN = new CMacro_Keyword("warn");
	public @S(20) @OPT PunctuationHyphen minus;
	public @S(30) CMacro_WarnWhat what; // 8004 8008 8066 perhaps
	
	public static class CMacro_WarnWhat extends TokenChooser
	{
		public @CHOICE CMacro_Number XXnumber;
		public @CHOICE CMacro_KeywordChoice XXwarnings = new CMacro_KeywordChoice(
				"aus",	/* orland: Assigned value is never used */
				"ccc",	/* orland: Condition is always true or false */
				"csu",	/* orland: Comparing signed and unsigned */
				"rch",	/* Borland: unreachable code */
				"spa"	/* orland: Suspicious pointer arithmetic */
		);
	}
}
