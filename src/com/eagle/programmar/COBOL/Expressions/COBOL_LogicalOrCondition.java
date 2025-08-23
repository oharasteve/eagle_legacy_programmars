// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;

public class COBOL_LogicalOrCondition extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) COBOL_Keyword OR = new COBOL_Keyword("OR");
	public @S(30) @OPT COBOL_RelationalOperator relationalOperator;
	public @S(40) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (relationalOperator != null && relationalOperator.isPresent())
		{
			throw new RuntimeException("Can't handle OR with relational operators yet");
		}
		boolean leftValue = interpreter.getBoolValue(left);
		boolean rightValue = interpreter.getBoolValue(right);
		interpreter.pushBool(leftValue || rightValue);
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator generator)
	{
		if (relationalOperator != null && relationalOperator.isPresent())
		{
			throw new RuntimeException("Can't handle OR with relational operators yet");
		}
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
	}
}
