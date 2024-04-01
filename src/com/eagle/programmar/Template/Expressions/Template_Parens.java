// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Template.Expressions;

import com.eagle.programmar.Template.Template_Expression;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Template_Parens extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Template_Expression expr;
	public @S(30) PunctuationRightParen rightParen;		
}
