// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.C.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Variable;
import com.eagle.programmar.C.Symbols.C_Identifier_Reference;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;

public class C_PostDecrementVariable extends PrimaryOperator implements EagleRunnable
{
	public @S(10) C_Variable var; // Cannot be just C_Expression -- infinite loop
	public @S(20) C_Punctuation postDecrementOperator = new C_Punctuation("--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = var.firstId.getWhich();
		if (!(which instanceof C_Identifier_Reference))
		{
			throw new RuntimeException("Unable to handle " + which + " now");
		}
		C_Identifier_Reference id = (C_Identifier_Reference) which;

		EagleValue val = interpreter.findSymbol(id.getValue());
		int prev = val.forceIntegerValue();
		EagleValue curr = new EagleInteger(prev - 1);
		interpreter.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), id.getValue(),
				curr);
		interpreter.pushInt(prev);
	}
}
