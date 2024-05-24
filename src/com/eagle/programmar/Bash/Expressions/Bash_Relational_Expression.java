// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Bash_Relational_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Bash_Expression left = new Bash_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Bash_RelOp operator;
	public @S(30) Bash_Expression right = new Bash_Expression(this, AllowedPrecedence.HIGHER);

	public static class Bash_RelOp extends TokenChooser
	{
		public @CHOICE Bash_PunctuationChoice strOp = new Bash_PunctuationChoice("==", "!=", "<", ">", "<=", ">=");
		public @CHOICE Bash_KeywordChoice numOp = new Bash_KeywordChoice("-eq", "-ne", "-lt", "-gt", "-le", "-ge");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int leftInt = interpreter.getIntValue(left);
		int rightInt = interpreter.getIntValue(right);
		switch (operator.getWhich().toString())
		{
		case "==", "-eq":
			interpreter.pushBool(leftInt == rightInt);
			return;
		case "!=", "-ne":
			interpreter.pushBool(leftInt != rightInt);
			return;
		case "<", "-lt":
			interpreter.pushBool(leftInt < rightInt);
			return;
		case "<=", "-le":
			interpreter.pushBool(leftInt <= rightInt);
			return;
		case ">", "-gt":
			interpreter.pushBool(leftInt > rightInt);
			return;
		case ">=", "-ge":
			interpreter.pushBool(leftInt >= rightInt);
			return;
		default:
			throw new RuntimeException("Unable to handle " + operator.getWhich().toString() + " with integers");
		}
	}
}
