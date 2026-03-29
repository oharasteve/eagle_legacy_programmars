// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Bash_Format = com.eagle.programmar.Bash.Bash_Format;
	using Bash_Function_Explicit = com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
	using Bash_EchoWhat = com.eagle.programmar.Bash.Terminals.Bash_EchoWhat;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_EchoCommand : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#index-echo") com.eagle.programmar.Bash.Terminals.Bash_Keyword ECHO = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("echo");
		public @DOC("#index-echo") Bash_Keyword ECHO = new Bash_Keyword("echo");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_EchoOption> options;
		public @OPT TokenList<Bash_EchoOption> options;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Bash_EchoWhat what;
		public @OPT Bash_EchoWhat what;

		public static class Bash_EchoOption extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Keyword XXopt = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("-n");
			public Bash_Keyword XXopt = new Bash_Keyword("-n");
		}

		public void interpret(EagleInterpreter interpreter)
		{
			string line = interpreter.getStrValue(what);
			string formatted = Bash_Format.format(interpreter, line);

			// if we are in a Function, it goes into a string and does not get printed
			Bash_Function_Explicit func = (Bash_Function_Explicit) interpreter.getCurrentFunction();
			if (func == null)
			{
				bool doNewLine = true;
				if (options != null)
				{
					foreach (Bash_EchoOption opt in options._elements)
					{
						if (opt.getWhich() is Bash_Keyword)
						{
							Bash_Keyword kw = (Bash_Keyword) opt.getWhich();
							if (kw.getValue().Equals("-n"))
							{
								doNewLine = false;
							}
						}
					}
				}

				if (doNewLine)
				{
					Console.WriteLine(formatted);
				}
				else
				{
					Console.Write(formatted);
				}
			}
			else
			{
				if (func._echoOutputs == null)
				{
					func._echoOutputs = formatted;
				}
				else
				{
					func._echoOutputs += "\n" + formatted;
				}
			}
		}
	}

}
