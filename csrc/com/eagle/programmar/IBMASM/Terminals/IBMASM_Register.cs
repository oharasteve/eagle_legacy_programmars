// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

namespace com.eagle.programmar.IBMASM.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class IBMASM_Register : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar >= recLen)
			{
				return false;
			}
			char ch = rec.charAt(_currentChar);
			if (("Rr" + DIGITS).IndexOf(ch) >= 0)
			{
				int endChar = _currentChar;
				while (true)
				{
					endChar++;
					if (endChar >= recLen)
					{
						break;
					}
					ch = rec.charAt(endChar);
					if (DIGITS.IndexOf(ch) < 0)
					{
						break;
					}
				}

				_id = rec.substring(_currentChar, endChar - _currentChar);
				foundIt(_currentLine, endChar - 1);
				return true;
			}
			return false;
		}
	}

}
