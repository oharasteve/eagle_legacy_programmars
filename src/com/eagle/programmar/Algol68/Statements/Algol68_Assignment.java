// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.Algol68.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.Algol68.Algol68_Expression;
import com.eagle.programmar.Algol68.Algol68_Type;
import com.eagle.programmar.Algol68.Algol68_Variable;
import com.eagle.programmar.Algol68.Symbols.Algol68_Identifier_Reference;
import com.eagle.programmar.Algol68.Terminals.Algol68_PunctuationChoice;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Algol68_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT Algol68_Type type;
	public @S(20) Algol68_Variable var;
	public @S(30) Algol68_PunctuationChoice equals = new Algol68_PunctuationChoice("=", "+=", ":=", "+:=");
	public @S(40) Algol68_Expression value;
	public @S(50) @OPT PunctuationSemicolon semicolon;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		int x = interpreter.getIntValue(value);
		EagleInteger val = new EagleInteger(x);
		Algol68_Identifier_Reference id = var.vars.first();
		interpreter._symbolTable.setSymbol(var.getFileName(), var.getStartLine(), var.getStartChar(), id.getValue(),
				val);
	}
}
