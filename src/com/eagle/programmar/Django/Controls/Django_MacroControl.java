// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 15, 2022

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Django_Element;
import com.eagle.programmar.Django.Django_Expression;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_MacroControl extends TokenSequence
{
	public @S(10) Django_Keyword MACRO = new Django_Keyword("macro");
	public @S(20) Django_Expression expr;
	public @S(30) @OPT PunctuationHyphen dash2;
	public @S(40) HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");

	public @S(50) TokenList<Django_Element> html;

	public @S(60) HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @S(70) @OPT PunctuationHyphen dash3;
	public @S(80) Django_Keyword ENDMACRO = new Django_Keyword("endmacro");
}
