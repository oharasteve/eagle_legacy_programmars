// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.Operator2Metrics.Oper2Types;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.programmar.Scala.Terminals.Scala_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.RelationalEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Scala_EqualsMethod extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Scala_Expression leftExpr = new Scala_Expression(this, AllowedPrecedence.HIGHER);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Scala_Keyword EQUALS = new Scala_Keyword("equals");
	public @S(40) PunctuationLeftParen leftParen;
	public @S(50) Scala_Expression rightExpr;
	public @S(60) PunctuationRightParen rightParen;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String left = interpreter.getStrValue(leftExpr);
		String right = interpreter.getStrValue(rightExpr);
		interpreter.pushBool(left.equals(right));
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression left = transformer.transformExpression(generator, leftExpr);
		AbstractExpression right = transformer.transformExpression(generator, rightExpr);
		Oper2Types types = new Oper2Types(TypeEnum.STRING, TypeEnum.STRING);
		return generator.newRelationalExpression(types, left, RelationalEnum.EQUALS, right, this);
	}
}
