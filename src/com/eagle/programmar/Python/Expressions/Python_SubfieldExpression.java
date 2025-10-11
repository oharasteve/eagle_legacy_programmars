// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Python_SubfieldExpression extends PrecedenceOperator
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @NOSPACE PunctuationPeriod dot;
	public @S(30) @NOSPACE Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	public Python_Expression generateSubfield(Python_Expression leftExpr,
			Python_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.dot = new PunctuationPeriod();
		this.right = rightExpr;
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
