// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenList;

public class Python_And_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Keyword AND = new Python_Keyword("and");
	public @S(30) @OPT TokenList<Python_Comment> comment;
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		if (leftValue)
		{
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(rightValue);
		}
		else
		{
			// Short circuit, don't bother with RHS
			interpreter.pushBool(false);
		}
	}
}
