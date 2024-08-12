// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.programmar.Javascript.Javascript_Expression;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Javascript_StringFunction extends PrimaryOperator
{
	public @S(10) Javascript_Keyword STRING = new Javascript_Keyword("String");
	public @S(20) PunctuationLeftParen leftParen;
	public @S(30) Javascript_Expression expr;
	public @S(40) PunctuationRightParen rightParen;
}
