// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

namespace com.eagle.programmar.VB.Terminals
{
	using TerminalPunctuationChoice = com.eagle.tokens.terminals.TerminalPunctuationChoice;

	public class VB_PunctuationChoice : TerminalPunctuationChoice
	{
		// Need default constructor for reading from the XML file
		public VB_PunctuationChoice() : base()
		{
		}

		public VB_PunctuationChoice(params string[] puncts) : base(puncts)
		{
		}
	}
}
