// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.PrecedenceOperator;

public class COBOL_RelationCondition extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) COBOL_Expression left = new COBOL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
	public @S(30) @OPT COBOL_Keyword NOT = new COBOL_Keyword("NOT");
	public @S(40) COBOL_RelationalOperator relationalOperator;
	public @S(50) COBOL_Expression right = new COBOL_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		String oper = relationalOperator.canonicalForm(); // Returns "<", "=", etc.
		boolean not = NOT.isPresent();
		boolean result;

		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = interpreter.getStrValue(left);
			String rightStr = interpreter.getStrValue(right);
			switch (oper)
			{
			case "=":
				result = leftStr.equals(rightStr);
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " for strings");
			}
		}
		else
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			switch (oper)
			{
			case "=":
				result = leftInt == rightInt;
				break;
			case "<":
				result = leftInt < rightInt;
				break;
			case "<=":
				result = leftInt <= rightInt;
				break;
			case ">":
				result = leftInt > rightInt;
				break;
			case ">=":
				result = leftInt >= rightInt;
				break;
			default:
				throw new RuntimeException("Unable to handle " + oper + " for integers");
			}
		}

		if (not) result = !result;
		interpreter.pushBool(result);
	}
}
