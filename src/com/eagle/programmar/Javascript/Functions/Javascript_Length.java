// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Javascript.Functions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Javascript.Javascript_Variable;
import com.eagle.programmar.Javascript.Symbols.Javascript_Identifier_Reference;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Javascript_Length extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Javascript_Variable variableName;
	public @S(20) PunctuationPeriod dot;
	public @S(30) Javascript_Keyword LENGTH = new Javascript_Keyword("length");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = variableName.firstId.getWhich();
		Javascript_Identifier_Reference id = (Javascript_Identifier_Reference) which;
		EagleValue val = interpreter.findSymbol(id.getValue());
		String str = val.forceStringValue();
		interpreter.pushInt(str.length());
	}
}
