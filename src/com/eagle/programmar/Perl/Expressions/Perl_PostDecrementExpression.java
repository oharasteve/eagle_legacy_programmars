// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Perl.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Perl.Perl_Variable;
import com.eagle.programmar.Perl.Perl_Variable.Perl_UserVariable;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.PrimaryOperator;

public class Perl_PostDecrementExpression extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Perl_Variable var;
	public @S(20) Perl_Punctuation postDecrementOperator = new Perl_Punctuation("--");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (var.getWhich() instanceof Perl_UserVariable)
		{
			Perl_UserVariable variable = (Perl_UserVariable) var.getWhich();
			EagleValue val = interpreter._symbolTable.findSymbol(variable.id.getValue());
			int prev = val.forceIntegerValue();
			EagleValue curr = new EagleInteger(prev - 1);
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), variable.id.getValue(),
					curr);
			interpreter.pushInt(prev);
		}
	}
}
