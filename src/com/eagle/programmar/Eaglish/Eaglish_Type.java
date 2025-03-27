// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class Eaglish_Type extends TokenChooser
{
	public @CHOICE Eaglish_KeywordChoice XXtypes = new Eaglish_KeywordChoice(
			"BOOLEAN", "INTEGER", "STRING");
}
