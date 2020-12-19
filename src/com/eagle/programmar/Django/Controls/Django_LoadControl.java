// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 16, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Symbols.Django_Variable_Definition;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_LoadControl extends TokenSequence
{
	public @S(10) HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_Keyword LOAD = new Django_Keyword("load");
	public @S(40) TokenList<Django_Variable_Definition> variables;
	public @S(50) @OPT PunctuationHyphen dash2;
	public @S(60) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
}
