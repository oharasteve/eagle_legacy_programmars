// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.Java.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Java_Variable;
import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Java_PostIncrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Java_Variable var;
	public @S(20) @NOSPACE Java_Punctuation postIncrementOperator = new Java_Punctuation("++");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.firstId.getWhich() instanceof Java_Identifier_Reference)
		{
			Java_Identifier_Reference id = (Java_Identifier_Reference) var.firstId.getWhich();
			EagleValue val = interpreter._symbolTable.findSymbol(id.getValue());
			int prev = val.forceIntegerValue();
			EagleValue curr = new EagleInteger(prev + 1);
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), id.getValue(),
					curr);
			interpreter.pushInt(prev);
		}
	}
}
