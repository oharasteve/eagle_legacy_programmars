// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_AutoEscapeControl extends TokenSequence
{
	public @S(10) HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_KeywordChoice AUTOESCAPE = new Django_KeywordChoice("autoescape", "endautoescape");
	public @S(40) @OPT Django_KeywordChoice OFF = new Django_KeywordChoice("false", "off", "on", "true");
	public @S(50) @OPT PunctuationHyphen dash2;
	public @S(60) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
}
