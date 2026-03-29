// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 5, 2022

namespace com.eagle.programmar.Go.Terminals
{
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Go_Literal : TerminalLiteralToken
	{
		public Go_Literal() : base("`\"", true, '\\', false, true)
		{
		}
	}

}
