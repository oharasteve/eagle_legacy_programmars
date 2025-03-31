// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class SQL_Builtin extends PrimaryOperator implements EagleRunnable
{
	public @S(10) SQL_KeywordChoice builtin = new SQL_KeywordChoice("FALSE", "NULL", "SYSTIMESTAMP", "TRUE");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtin.toString().toUpperCase())
		{
		case "FALSE":
			interpreter.pushBool(false);
			return;
		case "TRUE":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn: " + builtin);
	}
}
