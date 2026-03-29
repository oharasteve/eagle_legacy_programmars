// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class JavaP_Literal : TerminalLiteralToken
	{
		public JavaP_Literal() : base("\"", true, '\\', false, false)
		{
		}
	}

}
