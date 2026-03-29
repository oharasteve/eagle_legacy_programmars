// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;

	public class Basic_EndStatement : TokenSequence, EagleRunnableWithResult, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword END = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("END");
		public Basic_Keyword END = new Basic_Keyword("END");

		public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			return Eagle_Statement_Result.BREAK;
		}
	}

}
