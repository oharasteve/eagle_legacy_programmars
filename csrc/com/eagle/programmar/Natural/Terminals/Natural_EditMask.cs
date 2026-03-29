// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Natural_EditMask : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			char ch = rec.charAt(_currentChar);
			int parenCount = 0;
			bool inQuotes = ch == '\'';
			if (ch != ')')
			{
				int endChar = _currentChar;
				while (true)
				{
					endChar++;
					if (endChar >= recLen)
					{
						return false;
					}
					ch = rec.charAt(endChar);
					if (ch == '\'')
					{
						inQuotes = !inQuotes;
					}
					if (!inQuotes && ch == '(')
					{
						parenCount++;
					}
					if (!inQuotes && ch == ')')
					{
						if (parenCount == 0)
						{
							break;
						}
						parenCount--;
					}
				}
				foundIt(_currentLine, endChar - 1);
				_txt = rec.substring(_currentChar, endChar - _currentChar);
				return true;
			}
			return false;
		}

		public override string showString()
		{
			return "Edit Mask";
		}

		public override string description()
		{
			return "An edit mask, such as (EM=YYYY-MM-DD)";
		}
	}
}
