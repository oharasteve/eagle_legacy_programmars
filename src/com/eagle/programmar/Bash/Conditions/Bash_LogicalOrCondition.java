// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.LogicalOrEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class Bash_LogicalOrCondition extends PrecedenceOperator
		implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) Bash_Condition left = new Bash_Condition(this, AllowedPrecedence.ATLEAST);
	public @S(20) Bash_OrOperator oper;
	public @S(30) Bash_Condition right = new Bash_Condition(this, AllowedPrecedence.HIGHER);

	public static class Bash_OrOperator extends TokenChooser
	{
		public @CHOICE Bash_Punctuation XXbars = new Bash_Punctuation("||");
		
		public @CHOICE static class Bash_OrOperatorLiteral extends TokenSequence
		{
			public @S(10) PunctuationHyphen dash;
			public @S(20) Bash_Keyword OR = new Bash_Keyword("or");
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			// Short circuit, don't bother with RHS
			interpreter.pushBool(true);
		}
		else
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}
	}

	@Override
	public AbstractExpression transformExpression(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		return generator.newLogicalOrExpression(leftExpr, LogicalOrEnum.OR, rightExpr, this);
	}
}