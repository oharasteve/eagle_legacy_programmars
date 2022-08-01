// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 31, 2022

package com.eagle.programmar.CSS.Directives;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSS_AtProvide extends TokenSequence
{
	public @S(10) CSS_Punctuation AT = new CSS_Punctuation("@");
	public @S(20) CSS_Keyword PROVIDE = new CSS_Keyword("provide");
	public @S(30) CSS_Literal literal;
	public @S(40) PunctuationSemicolon semicolons;
}
