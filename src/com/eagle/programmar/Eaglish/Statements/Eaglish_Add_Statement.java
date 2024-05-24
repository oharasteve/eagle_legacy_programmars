// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.IntegerValue;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Variable;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Identifier_Reference;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Add_Statement extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword ADD = new Eaglish_Keyword("ADD");
	public @S(20) Eaglish_Expression expr;
	public @S(30) Eaglish_Keyword TO = new Eaglish_Keyword("TO");
	public @S(40) Eaglish_Variable var;
	public @S(50) Eaglish_EndOfLine eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(expr);
		int prev = interpreter.getIntValue(var);
		IntegerValue val = new IntegerValue(prev + x);

		AbstractToken which = var.var.getWhich();
		if (which instanceof Eaglish_Identifier_Reference)
		{
			Eaglish_Identifier_Reference id = (Eaglish_Identifier_Reference) which;
			interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), id.getValue(),
					val);
		}
		else
			throw new RuntimeException("Unable to process " + which);
	}
}
