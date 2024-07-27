// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.TCL.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.TCL.TCL_Expression;
import com.eagle.programmar.TCL.Terminals.TCL_KeywordChoice;
import com.eagle.programmar.TCL.Terminals.TCL_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class TCL_RelationalExpression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) TCL_Expression left = new TCL_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) TCL_RelOperator relOper;
	public @S(30) TCL_Expression right = new TCL_Expression(this, AllowedPrecedence.HIGHER);

	public static class TCL_RelOperator extends TokenChooser
	{
		public @CHOICE TCL_KeywordChoice XXEQ = new TCL_KeywordChoice(
				"lt", "le", "eq", "ne", "gt", "ge");
		public @CHOICE TCL_PunctuationChoice XXoperator = new TCL_PunctuationChoice(
				"<", ">", "<=", ">=", "==", "<>", "!=");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue leftValue = interpreter.getEagleValue(left);
		EagleValue rightValue = interpreter.getEagleValue(right);
		if (leftValue.isString() || rightValue.isString())
		{
			String leftStr = leftValue.forceStringValue();
			String rightStr = rightValue.forceStringValue();
			switch (relOper.getWhich().toString())
			{
			case "==", "eq":
				interpreter.pushBool(leftStr.equals(rightStr));
				return;
			case "!=", "ne":
				interpreter.pushBool(! leftStr.equals(rightStr));
				return;
			}
		}
		else
		{
			int leftInt = leftValue.forceIntegerValue();
			int rightInt = rightValue.forceIntegerValue();
			switch (relOper.getWhich().toString())
			{
			case "==", "eq":
				interpreter.pushBool(leftInt == rightInt);
				return;
			case "<>", "!=", "ne":
				interpreter.pushBool(leftInt != rightInt);
				return;
			case "<", "lt":
				interpreter.pushBool(leftInt < rightInt);
				return;
			case "<=", "le":
				interpreter.pushBool(leftInt <= rightInt);
				return;
			case ">", "gt":
				interpreter.pushBool(leftInt > rightInt);
				return;
			case ">=", "ge":
				interpreter.pushBool(leftInt >= rightInt);
				return;
			}
		}
		
		throw new RuntimeException("Unexpected relational operator: " + relOper.getWhich());
	}
}
