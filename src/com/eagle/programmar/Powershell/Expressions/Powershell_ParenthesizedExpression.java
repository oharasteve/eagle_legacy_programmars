// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Statement;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Powershell_ParenthesizedExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftParen leftParen;
	public @S(20) Powershell_ParenWhat what;

	public static class Powershell_ParenWhat extends TokenChooser
	{
		public @CHOICE Powershell_ParenExpression XXexpr;
		public @LAST Powershell_ParenStatement XXstmt;
	}

	public static class Powershell_ParenStatement extends TokenSequence
	{
		// Don't share right paren because expression might stop early, causing a failure
		public @S(10) Powershell_Statement statement;
		public @S(20) PunctuationRightParen rightParen;
	}

	public static class Powershell_ParenExpression extends TokenSequence
	{
		// Don't share right paren because expression might stop early, causing a failure
		public @S(10) Powershell_Expression expression;
		public @S(20) PunctuationRightParen rightParen;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (what.getWhich() instanceof Powershell_ParenExpression)
		{
			Powershell_ParenExpression expr = (Powershell_ParenExpression) what.getWhich();
			EagleValue value = interpreter.getEagleValue(expr.expression);
			interpreter.pushEagleValue(value);
		}
	}
}
