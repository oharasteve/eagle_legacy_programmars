// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 22, 2024

package com.eagle.programmar.Eaglish.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_KeywordChoice;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Eaglish_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Eaglish_Expression left = new Eaglish_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Eaglish_RelationalOperator operator;
	public @S(30) Eaglish_Expression right = new Eaglish_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class Eaglish_RelationalOperator extends TokenChooser
	{
		public @CHOICE Eaglish_PunctuationChoice operSymbol = new Eaglish_PunctuationChoice("=", "<", ">", "<=", ">=");
		public @CHOICE Eaglish_KeywordChoice operWord = new Eaglish_KeywordChoice("EQUALS", "NOT_EQUALS");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		
		if (leftValue.isString() && rightValue.isString())
		{
			String leftStr = interpreter.getStrValue(left);
			String rightStr = interpreter.getStrValue(right);
			AbstractToken which = operator.getWhich();
			if (which instanceof Eaglish_PunctuationChoice)
			{
				String oper = ((Eaglish_PunctuationChoice) which).getValue();
				switch (oper)
				{
				case "=":
					interpreter.pushBool(leftStr.equals(rightStr));
					break;
				default:
					throw new RuntimeException("Unable to handle " + oper);	
				}
			}
			else if (which instanceof Eaglish_KeywordChoice)
			{
				String oper = ((Eaglish_KeywordChoice) which).getValue();
				switch (oper)
				{
				case "EQUALS":
					interpreter.pushBool(leftStr.equals(rightStr));
					break;
				case "NOT_EQUALS":
					interpreter.pushBool(! leftStr.equals(rightStr));
					break;
				default:
					throw new RuntimeException("Unable to handle " + oper);	
				}
			}
			else throw new RuntimeException("Unable to handle " + which.getClass().getName());
		}

		else if (leftValue.isInteger() && rightValue.isInteger())
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			AbstractToken which = operator.getWhich();
			if (which instanceof Eaglish_PunctuationChoice)
			{
				String oper = ((Eaglish_PunctuationChoice) which).getValue();
				switch (oper)
				{
				case "=":
					interpreter.pushBool(leftInt == rightInt);
					break;
				case "<":
					interpreter.pushBool(leftInt < rightInt);
					break;
				case "<=":
					interpreter.pushBool(leftInt <= rightInt);
					break;
				case ">":
					interpreter.pushBool(leftInt > rightInt);
					break;
				case ">=":
					interpreter.pushBool(leftInt >= rightInt);
					break;
				default:
					throw new RuntimeException("Unable to handle " + oper);	
				}
			}
			else if (which instanceof Eaglish_KeywordChoice)
			{
				String oper = ((Eaglish_KeywordChoice) which).getValue();
				switch (oper)
				{
				case "EQUALS":
					interpreter.pushBool(leftInt == rightInt);
					break;
				case "NOT_EQUALS":
					interpreter.pushBool(leftInt != rightInt);
					break;
				default:
					throw new RuntimeException("Unable to handle " + oper);	
				}
			}
			else throw new RuntimeException("Unable to handle " + which.getClass().getName());
		}
	}
}