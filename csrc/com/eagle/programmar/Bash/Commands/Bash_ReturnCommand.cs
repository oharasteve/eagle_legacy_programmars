// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2024

namespace com.eagle.programmar.Bash.Commands
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Function_Explicit = com.eagle.programmar.Bash.Commands.Bash_Function.Bash_Function_Explicit;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Bash_ReturnCommand : TokenSequence, EagleRunnableWithResult, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#index-return") com.eagle.programmar.Bash.Terminals.Bash_Keyword RETURN = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("return");
		public @DOC("#index-return") Bash_Keyword RETURN = new Bash_Keyword("return");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Bash_Expression expr;
		public @OPT Bash_Expression expr;

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Bash_Function_Explicit func = (Bash_Function_Explicit) interpreter.getCurrentFunction();
			if (func != null)
			{
				int code = interpreter.getIntValue(expr);
				func._exitStatus = code;
			}
			return Eagle_Statement_Result.RETURN;
		}
	}

}
