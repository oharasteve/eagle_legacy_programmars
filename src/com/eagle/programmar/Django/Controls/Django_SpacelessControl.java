// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Django_SpacelessControl extends TokenSequence
{
	public @S(10) Django_KeywordChoice SPACELESS = new Django_KeywordChoice("spaceless", "endspaceless");
}
