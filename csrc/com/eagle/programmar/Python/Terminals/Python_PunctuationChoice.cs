// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

namespace com.eagle.programmar.Python.Terminals
{
	using TerminalPunctuationChoice = com.eagle.tokens.terminals.TerminalPunctuationChoice;

	public class Python_PunctuationChoice : TerminalPunctuationChoice
	{
		// Need default constructor for reading from the XML file
		public Python_PunctuationChoice() : base()
		{
		}

		public Python_PunctuationChoice(params string[] puncts) : base(puncts)
		{
		}
	}
}
