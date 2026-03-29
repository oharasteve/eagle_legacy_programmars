// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 31, 2024

namespace com.eagle.programmar.Lisp.Operators
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Lisp_KeywordChoice = com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Lisp_Builtins : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice builtins = new com.eagle.programmar.Lisp.Terminals.Lisp_KeywordChoice("T", "NIL");
		public Lisp_KeywordChoice builtins = new Lisp_KeywordChoice("T", "NIL");

		public override void interpret(EagleInterpreter interpreter)
		{
			switch (builtins.ToString().ToUpper())
			{
			case "NIL":
				interpreter.pushBool(false);
				return;
			case "T":
				interpreter.pushBool(true);
				return;
			}
			throw new Exception("Can't handle BuiltIn's other than T/NIL: " + builtins);
		}
	}
}
