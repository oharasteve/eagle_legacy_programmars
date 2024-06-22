// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.math.EagleInteger;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Variable;
import com.eagle.programmar.Bash.Terminals.Bash_Filename;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Bash_Assignment extends TokenSequence implements EagleRunnable
{
	public @S(10) @OPT Bash_Keyword LOCAL = new Bash_Keyword("local");
	public @S(20) @DOC("#Shell-Arithmetic") @OPT Bash_Keyword LET = new Bash_Keyword("let");
	public @S(30) Bash_Variable variable;
	public @S(40) Bash_PunctuationChoice equals = new Bash_PunctuationChoice("=", "+=", "-=");
	public @S(50) @OPT Bash_AssignWhat what;

	public static class Bash_AssignWhat extends TokenChooser
	{
		public @CHOICE Bash_Expression value;
		public @LAST Bash_Filename fname;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (!(what.getWhich() instanceof Bash_Expression))
		{
			throw new RuntimeException("Unexpected assignment variable: " + what.getWhich());
		}
		Bash_Expression expr = (Bash_Expression) what.getWhich();

		switch (equals.getValue())
		{
		case "=":
			int x = interpreter.getIntValue(expr);
			EagleInteger val = new EagleInteger(x);
			interpreter._symbolTable.setSymbol(variable.getFileName(), variable.getStartLine(), variable.getStartChar(),
					variable.id.getValue(), val);
			break;
		default:
			throw new RuntimeException("Unexpected assignment operator: " + equals.getValue());
		}
	}
}
