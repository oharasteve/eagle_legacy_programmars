// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class COBOL_ParenthesizedExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) COBOL_Expression expression;
	public @S(30) PunctuationRightParen rightParen;
}
