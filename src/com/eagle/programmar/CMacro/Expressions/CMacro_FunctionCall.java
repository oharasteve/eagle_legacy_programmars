// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

package com.eagle.programmar.CMacro.Expressions;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.CMacro.CMacroFunctionParens;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Terminals.CMacro_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.TokenChooser;

public class CMacro_FunctionCall extends PrimaryOperator implements EagleRunnable
{
	public @S(10) CMacro_Keyword DEFINED = new CMacro_Keyword("defined");
	public @S(20) CMacro_FunctionType funcType;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		AbstractToken which = funcType.getWhich();
		String name;
		if (which instanceof CMacro_Identifier_Reference)
		{
			name = ((CMacro_Identifier_Reference) which).toString();
		}
		else if (which instanceof CMacroFunctionParens)
		{
			name = ((CMacroFunctionParens) which).variable.toString();
		}
		else
		{
			throw new RuntimeException("Unexpected token: " + which.toString());
		}
		boolean val = interpreter._symbolTable.isDefined(name);
		interpreter.pushBool(val);
	}

	public static class CMacro_FunctionType extends TokenChooser
	{
		public @CHOICE CMacro_Identifier_Reference XXvariable;
		public @CHOICE CMacroFunctionParens XXparams;
	}
}
