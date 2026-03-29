// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 5, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using TerminalPunctuationChoice = com.eagle.tokens.terminals.TerminalPunctuationChoice;

	public class JavaP_PunctuationChoice : TerminalPunctuationChoice
	{
		// Need default constructor for reading from the XML file
		public JavaP_PunctuationChoice() : base()
		{
		}

		public JavaP_PunctuationChoice(params string[] puncts) : base(puncts)
		{
		}
	}

}
