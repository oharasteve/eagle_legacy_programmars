// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_SpacelessControl extends TokenSequence
{
	public @S(10) HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_KeywordChoice SPACELESS = new Django_KeywordChoice("spaceless", "endspaceless");
	public @S(40) @OPT PunctuationHyphen dash2;
	public @S(50) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
}
