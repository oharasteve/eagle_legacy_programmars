// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Django_AutoEscapeControl extends TokenSequence
{
	public @S(10) Django_KeywordChoice AUTOESCAPE = new Django_KeywordChoice("autoescape", "endautoescape");
	public @S(20) @OPT Django_KeywordChoice OFF = new Django_KeywordChoice("false", "off", "on", "true");
}
