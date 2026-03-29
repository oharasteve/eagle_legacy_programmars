// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template.Terminals
{
	using TerminalPunctuationToken = com.eagle.tokens.terminals.TerminalPunctuationToken;

	public class Template_Punctuation : TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Template_Punctuation() : this('\0')
		{
		}

		public Template_Punctuation(char punct) : base(punct)
		{
		}

		public Template_Punctuation(string punct) : base(punct)
		{
		}
	}

}
