// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.COBOL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator.COBOL_Equal;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator.COBOL_Greater;
import com.eagle.programmar.COBOL.COBOL_RelationalOperator.COBOL_Less;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
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
		
		if (leftValue.isInteger() && rightValue.isInteger())
		{
			int leftInt = interpreter.getIntValue(left);
			int rightInt = interpreter.getIntValue(right);
			AbstractToken which = relationalOperator.getWhich();
			boolean result;
			if (which instanceof COBOL_PunctuationChoice)
			{
				String oper = ((COBOL_PunctuationChoice) which).getValue();
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
					throw new RuntimeException("Unable to handle " + oper);	
				}
			}
			else if (which instanceof COBOL_Greater)
			{
				COBOL_Greater greater = (COBOL_Greater) which;
				if (greater.orEqual.isPresent())
				{
					result = leftInt >= rightInt;
				}
				else
				{
					result = leftInt > rightInt;
				}
			}
			else if (which instanceof COBOL_Equal)
			{
				result = leftInt == rightInt;
			}
			else if (which instanceof COBOL_Less)
			{
				COBOL_Less less = (COBOL_Less) which;
				if (less.orEqual.isPresent())
				{
					result = leftInt <= rightInt;
				}
				else
				{
					result = leftInt < rightInt;
				}
			}
			else throw new RuntimeException("Unable to handle " + which.getClass().getName());
			
			interpreter.pushBool(result);
		}
	}
}
