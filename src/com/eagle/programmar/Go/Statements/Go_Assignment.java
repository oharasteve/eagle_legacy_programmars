// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2022

package com.eagle.programmar.Go.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Go.Go_Expression;
import com.eagle.programmar.Go.Go_Variable;
import com.eagle.programmar.Go.Symbols.Go_Identifier_Reference;
import com.eagle.programmar.Go.Terminals.Go_EOLN;
import com.eagle.programmar.Go.Terminals.Go_PunctuationChoice;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Go_Assignment extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) SeparatedList<Go_Variable, PunctuationComma> vars;
	public @S(20) Go_PunctuationChoice equals = new Go_PunctuationChoice("=", "+=", "-=", "*=", "/=", ":=");
	public @S(30) Go_Expression value;
	public @S(40) Go_EOLN eoln;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		EagleValue val = interpreter.getEagleValue(value);
		Go_Variable var = vars.first();
		Go_Identifier_Reference id = var.vars.first();
		interpreter._symbolTable.setSymbol(id.getFileName(), id.getStartLine(), id.getStartChar(), id.toString(), val);
	}
}
