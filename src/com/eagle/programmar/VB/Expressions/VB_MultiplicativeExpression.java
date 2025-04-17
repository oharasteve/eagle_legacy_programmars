// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.VB.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.MultiplicativeEnum;
import com.eagle.transform.EagleTransformableExpression;
import com.eagle.transform.EagleTransformer;

public class VB_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable, EagleTransformableExpression
{
	public @S(10) VB_Expression left = new VB_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) VB_MultiplyOperation operator;
	public @S(30) VB_Expression right = new VB_Expression(this, AllowedPrecedence.HIGHER);

	public static class VB_MultiplyOperation extends TokenChooser
	{
		public @CHOICE VB_Keyword XXMOD = new VB_Keyword("mod");
		public @CHOICE VB_PunctuationChoice XXop = new VB_PunctuationChoice("*", "/", "\\");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = operator.getWhich();
		if (which instanceof TerminalToken)
		{
			String oper = ((TerminalToken) which).getValue();
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			switch (oper)
			{
			case "*":
				interpreter.pushInt(leftValue * rightValue);
				return;
			case "/":
				interpreter.pushDouble((double) leftValue / rightValue);
				return;
			case "mod":
				interpreter.pushInt(leftValue % rightValue);
				return;
			case "\\":
				interpreter.pushInt(leftValue / rightValue);
				return;
			}
		}
		throw new RuntimeException("Unable to handle " + operator + " in VB_MultiplicativeExpression");
	}
	
	@Override
	public AbstractExpression transformAdditive(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression leftExpr = transformer.transformExpression(generator, left);
		AbstractExpression rightExpr = transformer.transformExpression(generator, right);
		switch (operator.getWhich().toString())
		{
		case "*":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.TIMES, rightExpr, this);
		case "\\":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_TRUNCATE, rightExpr, this);
		case "/":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.DIVIDE_NO_TRUNCATE, rightExpr, this);
		case "mod":
			return generator.newMultiplicativeExpression(leftExpr, MultiplicativeEnum.REMAINDER, rightExpr, this);
		default:
			throw new RuntimeException("Unexpected multiplicative operator: " + operator.getWhich());
		}
	}
}
