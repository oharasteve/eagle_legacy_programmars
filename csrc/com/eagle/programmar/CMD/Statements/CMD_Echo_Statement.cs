// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 6, 2011

namespace com.eagle.programmar.CMD.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CMD_Keyword = com.eagle.programmar.CMD.Terminals.CMD_Keyword;
	using CMD_RestOfLine = com.eagle.programmar.CMD.Terminals.CMD_RestOfLine;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class CMD_Echo_Statement : TokenSequence, EagleRunnable, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("echo.mspx") com.eagle.programmar.CMD.Terminals.CMD_Keyword ECHO = new com.eagle.programmar.CMD.Terminals.CMD_Keyword("echo");
		public @DOC("echo.mspx") CMD_Keyword ECHO = new CMD_Keyword("echo");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationPeriod dot;
		public @OPT PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CMD.Terminals.CMD_RestOfLine line;
		public CMD_RestOfLine line;

		public void interpret(EagleInterpreter interpreter)
		{
			string result = interpreter.getStrValue(line);
			if (!result.Equals("OFF", StringComparison.OrdinalIgnoreCase))
			{
				Console.WriteLine(result);
			}
		}
	}

}
