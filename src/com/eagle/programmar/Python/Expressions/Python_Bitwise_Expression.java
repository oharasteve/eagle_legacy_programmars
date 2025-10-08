// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Generator;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.BitwiseEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Python_Bitwise_Expression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_PunctuationChoice operator = new Python_PunctuationChoice("&", "|", "^");
	public @S(30) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftValue = interpreter.getIntValue(left);
		int rightValue = interpreter.getIntValue(right);
		switch (operator.toString())
		{
		case "&":
			interpreter.pushInt(leftValue & rightValue);
			break;
		case "|":
			interpreter.pushInt(leftValue | rightValue);
			break;
		case "^":
			interpreter.pushInt(leftValue ^ rightValue);
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		BitwiseEnum oper;
		switch (operator.getValue())
		{
		case "&":
			oper = BitwiseEnum.AND;
			break;
		case "|":
			oper = BitwiseEnum.OR;
			break;
		case "^":
			oper = BitwiseEnum.XOR;
			break;
		default:
			throw new RuntimeException("Unable to handle " + operator);
		}
		return generator.newBitwiseExpression(leftExpr, oper, rightExpr, this);
	}

	public Python_Expression generateBitwise(Python_Expression leftExpr,
			BitwiseEnum oper, Python_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		switch (oper)
		{
		case AND:
			this.operator.setValue("&");
			break;
		case OR:
			this.operator.setValue("|");
			break;
		case XOR:
			this.operator.setValue("^");
			break;
		default:
			throw new RuntimeException("Unable to handle " + oper);
		}
		this.setTransformationSource(source);
		return Python_Generator.wrapExpression(this);
	}
}
