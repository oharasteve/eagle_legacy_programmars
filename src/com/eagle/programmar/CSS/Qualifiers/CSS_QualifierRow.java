// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 12, 2022

package com.eagle.programmar.CSS.Qualifiers;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class CSS_QualifierRow extends TokenSequence
{
	public @S(10) CSS_Keyword ROW = new CSS_Keyword("row$");
	public @S(20) PunctuationEquals equals;
	public @S(30) CSS_Literal literal;
}