// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Django_Variable;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_ForControl extends TokenSequence
{
	public @S(10) HTML_Punctuation bracePercent = new HTML_Punctuation("{%");
	public @S(20) @OPT PunctuationHyphen dash1;
	public @S(30) Django_Keyword FOR = new Django_Keyword("for");
	public @S(40) Django_Variable var;
	public @S(50) Django_Keyword IN = new Django_Keyword("in");
	public @S(60) Django_Expression expr;
	public @S(70) @OPT PunctuationHyphen dash2;
	public @S(80) HTML_Punctuation percentBrace = new HTML_Punctuation("%}");
	
	public @S(90) TokenList<Django_Element> html;

	public @S(100) HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @S(110) @OPT PunctuationHyphen dash3;
	public @S(120) Django_Keyword ENDFOR = new Django_Keyword("endfor");
	public @S(130) @OPT PunctuationHyphen dash4;
	public @S(140) HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
}
