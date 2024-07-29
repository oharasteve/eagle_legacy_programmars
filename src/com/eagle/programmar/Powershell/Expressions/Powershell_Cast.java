// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Powershell.Powershell_Expression;
import com.eagle.programmar.Powershell.Powershell_Type;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;

public class Powershell_Cast extends PrimaryOperator implements EagleRunnable
{
	public @S(10) PunctuationLeftBracket leftBracket;
	public @S(20) Powershell_Type type;
	public @S(30) PunctuationRightBracket rightBracket;
	public @S(40) Powershell_Expression expr;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String typeName = type.base.getWhich().toString();
		if (typeName.toLowerCase().startsWith("int"))
		{
			int val = interpreter.getIntValue(expr);
			interpreter.pushInt(val);
		}
	}
}
