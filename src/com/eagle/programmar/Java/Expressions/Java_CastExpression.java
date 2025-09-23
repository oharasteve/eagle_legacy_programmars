// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.programmar.Java.Java_Expression;
import com.eagle.programmar.Java.Java_Generator;
import com.eagle.programmar.Java.Java_Type;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.punctuation.PunctuationAmpersand;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_CastExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @NOSPACE SeparatedList<Java_Type, PunctuationAmpersand> types;
	public @S(30) @NOSPACE PunctuationRightParen rightParen;
	public @S(40) Java_Expression expr;

	public static Java_Expression newCastExpression(Java_Type type,
			Java_Expression expr, AbstractToken source)
	{
		Java_CastExpression cast = new Java_CastExpression();
		cast.leftParen = new PunctuationLeftParen();
		cast.types = new SeparatedList<Java_Type, PunctuationAmpersand>();
		cast.types.addPrimaryElement(type);
		cast.rightParen = new PunctuationRightParen();

		if (expr.getWhich() instanceof Java_ParenthesizedExpression)
		{
			cast.expr = expr;
		}
		else
		{
			Java_ParenthesizedExpression parens = new Java_ParenthesizedExpression();
			cast.expr = parens.generateParentheses(expr, expr);
		}

		cast.setTransformationSource(source);
		return Java_Generator.wrapExpression(cast);
	}
}
