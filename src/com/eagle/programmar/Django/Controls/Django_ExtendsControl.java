// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_ExtendsControl extends TokenSequence
{
	public @S(10) HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_Keyword EXTENDS = new Django_Keyword("extends");
	public @S(40) Django_Literal literal;
	public @S(50) @OPT PunctuationHyphen dash2;
	public @S(60) HTML_Punctuation percentBrace = new HTML_Punctuation("%}");
}
