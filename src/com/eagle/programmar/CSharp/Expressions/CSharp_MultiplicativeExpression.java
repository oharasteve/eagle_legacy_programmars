// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.MultiplicativeEnum;
import com.eagle.generate.Expressions.Eagle_Generate_Multiplicative;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.metrics.Operator2Metrics;
import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Generator;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.Terminals.CSharp_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class CSharp_MultiplicativeExpression extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression,
				Eagle_Generate_Multiplicative<CSharp_Expression>
{
	public @S(10) CSharp_Expression left = new CSharp_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @DOC("operators/arithmetic-operators") CSharp_PunctuationChoice operator = new CSharp_PunctuationChoice("*", "/", "%");
	public @S(30) CSharp_Expression right = new CSharp_Expression(this, AllowedPrecedence.HIGHER);

	private @SKIP Operator2Metrics _metrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = operator.toString();
		
		if (_metrics == null)
		{
			_metrics = new Operator2Metrics(interpreter._metrics, operator, oper);
		}
		_metrics.operated(leftValue.typeName(), rightValue.typeName());
		
		int leftInt = leftValue.forceIntegerValue();
		int rightInt = rightValue.forceIntegerValue();
		switch (oper)
		{
		case "*":
			interpreter.pushInt(leftInt * rightInt);
			return;
		case "/":
			interpreter.pushInt(leftInt / rightInt);
			return;
		case "%":
			interpreter.pushInt(leftInt % rightInt);
			return;
		}
		throw new RuntimeException("Unexpected multiplicative operator: " + oper);
	}
	
	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.toString())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "%":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator);
		}
	}
	
	@Override
	public CSharp_Expression generateMultiplicative(
			CSharp_Expression leftExpr, MultiplicativeEnum oper,
			CSharp_Expression rightExpr, AbstractToken source)
	{
		this.left = leftExpr;
		this.right = rightExpr;
		switch (oper)
		{
		case TIMES:
			this.operator.setValue("*");
			break;
		case DIVIDE_TRUNCATE:
			this.operator.setValue("/");
			break;
		case DIVIDE_NO_TRUNCATE:
			this.operator.setValue("/");
			CSharp_Type type = CSharp_Type.newPrimitiveType("double");
			CSharp_CastExpression cast = CSharp_CastExpression.newCastExpression(type, this.right);
			this.right = CSharp_Generator.wrapExpression(cast);
			break;
		case REMAINDER:
			this.operator.setValue("%");
			break;
		default:
			throw new RuntimeException("Unable to handle: " + oper);
		}
		this.setTransformationSource(source);
		return CSharp_Generator.wrapExpression(this);
	}
}
