// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.CSharp.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class CSharp_PostIncrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CSharp_Variable var;
	public @S(20) @NOSPACE CSharp_Punctuation postIncrementOperator = new CSharp_Punctuation("++");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.firstId.getWhich() instanceof CSharp_Identifier_Reference)
		{
			CSharp_Identifier_Reference id = (CSharp_Identifier_Reference) var.firstId.getWhich();
			EagleValue val = interpreter.findSymbol(id.getValue());
			int prev = val.forceIntegerValue();
			EagleValue curr = new EagleInteger(prev + 1);
			interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), id.getValue(),
					curr);
			interpreter.pushInt(prev);
		}
	}
}
