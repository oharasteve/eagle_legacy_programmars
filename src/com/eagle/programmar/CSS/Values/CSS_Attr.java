// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSS_Attr extends TokenSequence
{
	public @S(10) CSS_Keyword ATTR = new CSS_Keyword("attr");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) CSS_KeywordChoice CODE = new CSS_KeywordChoice("href", "title");
	public @S(40) PunctuationRightParen rightParen;
}