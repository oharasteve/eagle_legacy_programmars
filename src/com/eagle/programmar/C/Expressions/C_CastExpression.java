// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Type;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class C_CastExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) C_Type ctype;
	public @S(30) PunctuationRightParen rightParen;
	public @S(40) C_Expression expr;
}
