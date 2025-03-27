// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

package com.eagle.programmar.Powershell.Expressions;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Powershell.Terminals.Powershell_KeywordChoice;
import com.eagle.tokens.PrimaryOperator;

public class Powershell_BuiltIn extends PrimaryOperator implements EagleRunnable
{
	public @S(10) Powershell_KeywordChoice builtin = new Powershell_KeywordChoice("length", "$False", "$True");

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		switch (builtin.toString())
		{
		case "$False":
			interpreter.pushBool(false);
			return;
		case "$True":
			interpreter.pushBool(true);
			return;
		}
		throw new RuntimeException("Can't handle BuiltIn's other than $true/$false: " + builtin);
	}
}
