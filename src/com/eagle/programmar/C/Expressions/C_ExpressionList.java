// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_ArgumentList;
import com.eagle.programmar.C.C_ArgumentList.C_MoreArgument;
import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class C_ExpressionList extends PrimaryOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) PunctuationLeftBrace leftBrace;
	public @S(20) @OPT C_ArgumentList valueList;
	public @S(30) @OPT C_Comment comment;
	public @S(40) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleArray array = new EagleArray();
		EagleValue val = interpreter.getEagleValue(valueList.arg.getWhich());
		array.addValue(val);
		for (C_MoreArgument arg : valueList.moreArgs._elements)
		{
			val = interpreter.getEagleValue(arg.arg);
			array.addValue(val);
		}
		interpreter.pushEagleValue(array);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
		if (valueList != null && valueList.isPresent())
		{
			// First arg
			AbstractToken which1 = valueList.arg.getWhich();
			if (which1 instanceof C_Expression)
			{
				C_Expression expr1 = (C_Expression) which1;
				AbstractExpression newExpr1 = transformer.transformExpression(generator, expr1);
				exprs.add(newExpr1);
			}

			// Remaining args
			if (valueList.moreArgs != null && valueList.moreArgs.size() > 0)
			{
				for (C_MoreArgument more : valueList.moreArgs._elements)
				{
					AbstractToken which2 = more.arg.getWhich();
					if (which2 instanceof C_Expression)
					{
						C_Expression expr2 = (C_Expression) which2;
						AbstractExpression newExpr2 = transformer.transformExpression(generator, expr2);
						exprs.add(newExpr2);
					}
				}
			}
		}
		return generator.newArrayExpression(exprs, this);
	}
}
