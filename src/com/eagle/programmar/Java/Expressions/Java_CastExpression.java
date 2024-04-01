// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_CastExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) SeparatedList<Java_Type, PunctuationAmpersand> types;
	public @S(30) PunctuationRightParen rightParen;
	public @S(40) Java_Expression expr;
}
