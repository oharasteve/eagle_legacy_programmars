// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

namespace com.eagle.programmar.PLI.Terminals
{
	using TerminalLevelToken = com.eagle.tokens.terminals.TerminalLevelToken;

	public class PLI_Level : TerminalLevelToken
	{
		protected internal override bool validateLevel()
		{
			// Passed all the tests!
			return true;
		}

		public override string description()
		{
			return "PL/I level number.";
		}
	}

}
