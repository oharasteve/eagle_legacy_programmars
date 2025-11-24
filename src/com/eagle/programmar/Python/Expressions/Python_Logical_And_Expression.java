// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Logical_And_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Keyword AND = new Python_Keyword("and");
	public @S(30) @OPT TokenList<Python_Comment> comment;
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}
		else
		{
			// Short circuit, don't bother with RHS
			interpreter.pushBool(false);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalAndExpression(leftExpr, rightExpr, this);
	}

	public Python_Expression generateLogicalAnd(Python_Expression leftExpr,
			Python_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
