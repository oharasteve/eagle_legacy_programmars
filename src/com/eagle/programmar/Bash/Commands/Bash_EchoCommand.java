// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
import com.eagle.programmar.Bash.Terminals.Bash_Argument;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_LiteralExpression;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_EchoCommand extends TokenSequence implements EagleRunnable, AbstractStatement
{
	public @S(10) @DOC("#index-echo") Bash_Keyword ECHO = new Bash_Keyword("echo");
	public @S(20) @OPT TokenList<Bash_EchoOption> options;
	public @S(30) @OPT Bash_EchoWhat what;

	public static class Bash_EchoWhat extends TokenChooser
	{
		public @FIRST Bash_Expression XXexpr;
		public @CHOICE Bash_Argument XXargument;
	}
	
	public static class Bash_EchoOption extends TokenChooser
	{
		public @CHOICE Bash_Keyword XXopt = new Bash_Keyword("-n");
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		ArgumentsMetrics metrics = null;
		String fmt;
		AbstractToken which = what.getWhich();
		if (which instanceof Bash_Expression)
		{
			Bash_Expression expr = (Bash_Expression) which;
			fmt = interpreter.getStrValue(expr);
		}
		else if (which instanceof Bash_Argument)
		{
			Bash_Argument arg = (Bash_Argument) which;
			fmt = arg.getValue();
		}
		else
		{
			throw new RuntimeException("Unexpected echo value: " + which);
		}
		String value = Bash_LiteralExpression.interpret(interpreter, fmt, metrics);

		// if we are in a Function, it goes into a string and does not get printed
		Bash_Function_Explicit func = (Bash_Function_Explicit) interpreter.getCurrentFunction();
		if (func == null)
		{
			boolean doNewLine = true;
			if (options != null)
			{
				for (Bash_EchoOption opt : options._elements)
				{
					if (opt.getWhich() instanceof Bash_Keyword)
					{
						Bash_Keyword kw = (Bash_Keyword) opt.getWhich();
						if (kw.getValue().equals("-n"))
						{
							doNewLine = false;
						}
					}
				}
			}

			if (doNewLine)
			{
				System.out.println(value);
			}
			else
			{
				System.out.print(value);
			}
		}
		else
		{
			if (func._echoOutputs == null)
			{
				func._echoOutputs = value;
			}
			else
			{
				func._echoOutputs += "\n" + value;
			}
		}
	}
}
