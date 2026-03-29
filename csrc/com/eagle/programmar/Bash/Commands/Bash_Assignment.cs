// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_Filename = com.eagle.programmar.Bash.Terminals.Bash_Filename;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_PunctuationChoice = com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_Assignment : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Bash_Keyword LOCAL = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("local");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("#Shell-Arithmetic") @OPT Bash_Keyword LET = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("let");
		public @DOC("#Shell-Arithmetic") Bash_Keyword LET = new Bash_Keyword("let");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_Variable variable;
		public Bash_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice equals = new com.eagle.programmar.Bash.Terminals.Bash_PunctuationChoice("=", "+=", "-=");
		public Bash_PunctuationChoice equals = new Bash_PunctuationChoice("=", "+=", "-=");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Bash_AssignWhat what;
		public @OPT Bash_AssignWhat what;

		public static class Bash_AssignWhat extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_Expression XXvalue;
			public Bash_Expression XXvalue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Bash_Filename XXfname;
			public Bash_Filename XXfname;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			if (!(what.getWhich() is Bash_Expression))
			{
				throw new Exception("Unexpected assignment variable: " + what.getWhich());
			}
			Bash_Expression expr = (Bash_Expression) what.getWhich();

			switch (equals.getValue())
			{
			case "=":
				EagleValue val = interpreter.getEagleValue(expr);
				interpreter.setSymbol(variable, variable.id.getValue(), val);
				break;
			default:
				throw new Exception("Unexpected assignment operator: " + equals.getValue());
			}
		}
	}

}
