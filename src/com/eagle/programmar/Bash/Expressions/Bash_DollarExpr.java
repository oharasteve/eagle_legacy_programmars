// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Bash.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;

public class Bash_DollarExpr extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_Punctuation dollar = new Bash_Punctuation("$");
	public @S(20) PunctuationLeftBrace leftBrace;
	public @S(30) Bash_Expression expr;
	public @S(40) PunctuationRightBrace rightBrace;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		interpreter.tryToInterpret(expr);
	}
}
