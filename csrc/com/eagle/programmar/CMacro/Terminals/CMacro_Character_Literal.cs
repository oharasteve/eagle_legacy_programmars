// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 30, 2020

namespace com.eagle.programmar.CMacro.Terminals
{
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CMacro_Character_Literal : TerminalLiteralToken
	{
		public CMacro_Character_Literal() : base("'", true, '\\', false, false)
		{
		}
	}
}
