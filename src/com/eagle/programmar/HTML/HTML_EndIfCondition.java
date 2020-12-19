// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class HTML_EndIfCondition extends TokenSequence
{
	public @S(10) HTML_Punctuation startTag = new HTML_Punctuation("<!");
	public @S(20) PunctuationLeftBracket leftBracket;
	public @S(30) HTML_Keyword ENDIF = new HTML_Keyword("endif");
	public @S(40) PunctuationRightBracket rightBracket;
	public @S(50) HTML_Punctuation endTag = new HTML_Punctuation('>');
}
