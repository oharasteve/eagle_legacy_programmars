// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public class SQL_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			// Have to see if it starts with a quote first!
			EagleLineReader rec = lines.get(_currentLine);
			char ch = rec.charAt(_currentChar);
			if (ch == '\'' || ch == '`' || ch == '"')
			{
				int recLen = rec.length();
				char quote = ch;
				int endChar = _currentChar;
				char prevCh = ' ';
				while (true)
				{
					endChar++;
					if (endChar >= recLen)
					{
						break;
					}
					ch = rec.charAt(endChar);

					if (ch == quote && prevCh != '\\')
					{
						break;
					}
					if (prevCh == '\\' && ch == '\\')
					{
						ch = ' ';
					}

					prevCh = ch;
				}
				foundIt(_currentLine, endChar);
				_id = rec.substring(_currentChar, (endChar + 1) - _currentChar);
				return true;
			}

			return genericIdentifier(lines, ALPHAS, ALPHAS + DIGITS + "_", true, false);
		}
	}

}
