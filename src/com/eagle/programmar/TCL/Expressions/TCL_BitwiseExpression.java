// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, TCL

package com.eagle.programmar.TCL.Expressions;

import com.eagle.generate.BitwiseEnum;
import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class TCL_BitwiseExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) TCL_PunctuationChoice operator = new TCL_PunctuationChoice("&", "|", "^");
	public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

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
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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
}
