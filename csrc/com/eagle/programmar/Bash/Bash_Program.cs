// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

namespace com.eagle.programmar.Bash
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Bash_Function = com.eagle.programmar.Bash.Commands.Bash_Function;
	using Bash_Function_Explicit = com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;

	public class Bash_Program : AbstractLanguage, EagleRunnable
	{
		public const string BASH = "Bash";

		public Bash_Program() : base(BASH, new Bash_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://www.gnu.org/savannah-checkouts/gnu/bash/manual/bash.html";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Bash_Element> statements;
		public TokenList<Bash_Element> statements;

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (Bash_Element stmt in statements._elements)
			{
				AbstractToken which = stmt.element.getWhich();
				if (which is Bash_Function)
				{
					Bash_Function fn = (Bash_Function) which;
					if (fn.getWhich() is Bash_Function.Bash_Function_Explicit)
					{
						Bash_Function.Bash_Function_Explicit func = (Bash_Function.Bash_Function_Explicit) fn.getWhich();
						interpreter.addFunction(func.id.getValue(), func);
					}
				}
			}

			// Second pass, execute the program
			foreach (Bash_Element stmt in statements._elements)
			{
				interpreter.tryToInterpret(stmt.element);
			}
		}
	}

}
