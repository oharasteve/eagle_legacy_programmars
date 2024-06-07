// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.AWK.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.Terminals.AWK_Literal;
import com.eagle.tokens.PrimaryOperator;

public class AWK_String extends PrimaryOperator implements EagleRunnable
{
	public @S(10) AWK_Literal literal;
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		String str = literal.getValue();
		if (str.startsWith("\"") || str.startsWith("'"))
		{
			str = str.substring(1, str.length()-1);	// Remove quotes
		}
		interpreter.pushStr(str);
	}
}
