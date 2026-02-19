// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_CastExpression extends PrimaryOperator
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) @NOSPACE CSharp_Type type;
	public @S(30) @NOSPACE PunctuationRightParen rightParen;
	public @S(40) CSharp_Expression expr;

	public static CSharp_Expression newCastExpression(CSharp_Type type,
			CSharp_Expression expr, AbstractToken source)
	{
		CSharp_CastExpression cast = new CSharp_CastExpression();
		cast.leftParen = new PunctuationLeftParen();
		cast.type = type;
		cast.rightParen = new PunctuationRightParen();

		if (expr.getWhich() instanceof CSharp_ParenthesizedExpression)
		{
			cast.expr = expr;
		}
		else
		{
			cast.expr = CSharp_ParenthesizedExpression.generateParentheses(expr, expr);
		}

		cast.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(cast);
	}
}
