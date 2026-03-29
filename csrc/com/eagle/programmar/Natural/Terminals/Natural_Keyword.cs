// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class Natural_Keyword : TerminalKeywordToken
	{
		// Need default constructor for reading from the XML file
		public Natural_Keyword() : this("")
		{
		}

		public Natural_Keyword(string word) : base(word)
		{

			// Dang, didn't work. No instances are created in time ...
			// Add it to the global list of reserved keywords
			// if (!Natural_Program.keywords.contains(word))
			// Natural_Program.keywords.put(word, word);
		}
	}

}
