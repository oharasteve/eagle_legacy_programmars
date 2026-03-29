// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

namespace com.eagle.programmar.C.Terminals
{
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class C_Literal : TerminalLiteralToken
	{
		public C_Literal() : base("\"", true, '\\', false, false)
		{
		}
	}

}
