// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Python_FunnyConstructor extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen1;
	public @S(20) Python_Type type;
	public @S(30) PunctuationStar star;
	public @S(40) Python_Expression size;
	public @S(50) PunctuationRightParen rightParen1;
	public @S(60) PunctuationLeftParen leftParen2;
	public @S(70) PunctuationRightParen rightParen2;
}
