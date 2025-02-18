// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx;

import com.eagle.programmar.Rexx.Terminals.Rexx_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Rexx_Subscript extends TokenSequence
{
	public @S(10) PunctuationPeriod dot;
	public @S(20) Rexx_Subscr subscr;
	
	public static class Rexx_Subscr extends TokenChooser
	{
		public @CHOICE Rexx_Number XXnumericSubscr;
		public @CHOICE Rexx_Variable XXvariableSubscr;
	}
}
