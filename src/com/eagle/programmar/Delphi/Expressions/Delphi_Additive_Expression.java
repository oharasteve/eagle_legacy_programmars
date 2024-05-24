// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Delphi.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Terminals.Delphi_KeywordChoice;
import com.eagle.programmar.Delphi.Terminals.Delphi_PunctuationChoice;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;

public class Delphi_Additive_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Delphi_Expression left = new Delphi_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Delphi_Additive_Operator addOp;
	public @S(30) Delphi_Expression right = new Delphi_Expression(this, AllowedPrecedence.HIGHER);

	public static class Delphi_Additive_Operator extends TokenChooser
	{
		public @CHOICE Delphi_PunctuationChoice operator = new Delphi_PunctuationChoice("+", "-");
		public @CHOICE Delphi_KeywordChoice OR = new Delphi_KeywordChoice("Or", "Xor");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (addOp.getWhich().toString())
		{
		case "+":
			int leftValue = interpreter.getIntValue(left);
			int rightValue = interpreter.getIntValue(right);
			interpreter.pushInt(leftValue + rightValue);
			return;
		case "-":
			leftValue = interpreter.getIntValue(left);
			rightValue = interpreter.getIntValue(right);
			interpreter.pushInt(leftValue - rightValue);
			return;
		case "Or":
			boolean leftVal = interpreter.getBoolValue(left);
			boolean rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(leftVal || rightVal);
			return;
		case "Xor":
			leftVal = interpreter.getBoolValue(left);
			rightVal = interpreter.getBoolValue(right);
			interpreter.pushBool(leftVal ^ rightVal);
			return;
		default:
			throw new RuntimeException("Unexpected additive operator: " + addOp.getWhich());
		}
	}
}
