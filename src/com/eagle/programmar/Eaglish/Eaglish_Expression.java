// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Literal;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Number;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.PrecedenceChooser;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.PrecedenceOperator.AllowedPrecedence;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Eaglish_Expression extends PrecedenceChooser implements AbstractExpression
{
	private static OperatorList _operators = new OperatorList();

	public @P(10) Eaglish_Number number;
	public @P(20) Eaglish_Literal literal;

	//
	// Note: All operators should stay in @P(#) order. This determines operator precedence.
	//

	public Eaglish_Expression()
	{
	    super(_operators);
	}

	public Eaglish_Expression(PrecedenceOperator token, AllowedPrecedence allowed)
	{
	    super(_operators, allowed, token.getClass());
	}
		
	///////////////////////////////////////////////
	// Primary expressions

	public static @P(100) class Eaglish_NegativeExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("-", "+");
		public @S(20) Eaglish_Expression expr;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int value = interpreter.getIntValue(expr);
			String oper = operator.getValue();
			switch (oper)
			{
			case "-" :
				break;
			case "+" :
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_NegativeExpression");	
			}
			interpreter.pushInt(-value);
		}
	}

	public static @P(110) class Eaglish_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) Eaglish_Expression expr;
		public @S(30) PunctuationRightParen rightParen;
		
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int value = interpreter.getIntValue(expr);
			interpreter.pushInt(value);
		}
	}

	///////////////////////////////////////////////
	// Binary expressions

	public static @P(500) class Eaglish_MultiplicativeExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("*", "/");
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			String oper = operator.getValue();
			switch (oper)
			{
			case "*":
				interpreter.pushInt(leftValue * rightValue);
				break;
			case "/":
				interpreter.pushInt(leftValue / rightValue);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_MultiplicativeExpression");	
			}
		}
	}

	public static @P(510) class Eaglish_AdditiveExpression extends PrecedenceOperator implements EagleRunnable
	{
		public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
		public @S(20) Eaglish_PunctuationChoice operator = new Eaglish_PunctuationChoice("+", "-");
		public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	
		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			String oper = operator.getValue();
			switch (oper)
			{
			case "+":
				interpreter.pushInt(leftValue + rightValue);
				break;
			case "-":
				interpreter.pushInt(leftValue - rightValue);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " in Eaglish_AdditiveExpression");	
			}
		}
	}
}