// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Qualifiers;

import com.eagle.programmar.CSS.CSS_Value;
import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationStar;

public class CSS_QualifierClass extends TokenSequence
{
	public @S(10) CSS_Keyword CLASS = new CSS_Keyword("class");
	public @S(20) PunctuationStar star;
	public @S(30) PunctuationEquals equals;
	public @S(40) CSS_Value value;
}