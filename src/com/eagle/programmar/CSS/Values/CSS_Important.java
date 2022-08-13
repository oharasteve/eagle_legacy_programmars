// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Values;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSS_Important extends TokenSequence
{
	public @S(10) CSS_Punctuation exclamation = new CSS_Punctuation('!');
	public @S(20) CSS_Keyword IMPORTANT = new CSS_Keyword("important");
}