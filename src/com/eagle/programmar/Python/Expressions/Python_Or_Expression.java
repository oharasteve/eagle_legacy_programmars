// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Python.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Python_Or_Expression extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Python_Expression left = new Python_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) Python_Or_Operation operator;
	public @S(30) @OPT TokenList<Python_Comment> comment;
	public @S(40) Python_Expression right = new Python_Expression(this, AllowedPrecedence.HIGHER);
	
	public static class Python_Or_Operation extends TokenChooser
	{
		public @CHOICE Python_Keyword OR = new Python_Keyword("or");
		public @CHOICE Python_Punctuation caret = new Python_Punctuation("^");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		boolean leftValue = interpreter.getBoolValue(left);
		switch (operator.getWhich().toString())
		{
		case "or":
			if (leftValue)
			{
				// Short circuit, don't bother with RHS
				interpreter.pushBool(true);
			}
			else
			{
				boolean rightValue = interpreter.getBoolValue(right);
				interpreter.pushBool(rightValue);
			}
			return;
		case "^":
			boolean rightValue = interpreter.getBoolValue(right);
			interpreter.pushBool(leftValue ^ rightValue);	// Exclusive or, XOR
			return;
		}
		throw new RuntimeException("Unexpected OR operator: " + operator);
	}
}
