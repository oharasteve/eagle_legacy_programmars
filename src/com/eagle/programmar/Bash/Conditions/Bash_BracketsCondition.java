// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

package com.eagle.programmar.Bash.Conditions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Bash_BracketsCondition extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Bash_Punctuation leftBrackets = new Bash_Punctuation("[[");
	public @S(20) Bash_Condition condition;
	public @S(30) Bash_Punctuation rightBrackets = new Bash_Punctuation("]]");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean cond = interpreter.getBoolValue(condition);
		interpreter.pushBool(cond);
	}
}
