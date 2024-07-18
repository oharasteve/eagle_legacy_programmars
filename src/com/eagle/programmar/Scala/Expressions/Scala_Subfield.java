// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Scala.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Scala_Expression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrecedenceOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Scala_Subfield extends PrecedenceOperator implements EagleRunnable
{
	public @S(10) Scala_Expression left = new Scala_Expression(this, AllowedPrecedence.ATLEAST);
	public @S(20) PunctuationPeriod dot;
	public @S(30) Scala_Expression right = new Scala_Expression(this, AllowedPrecedence.HIGHER);

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = right.getWhich();
		if (which instanceof Scala_BuiltInFunction)
		{
			Scala_BuiltInFunction builtin = (Scala_BuiltInFunction) which;
			builtin.processSubfield(interpreter, left);
		}
		else
		{
			throw new RuntimeException("Unexpected subfield expression");
		}
	}
}
