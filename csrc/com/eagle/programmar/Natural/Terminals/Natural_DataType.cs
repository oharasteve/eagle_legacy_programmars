// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 10, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalPictureToken = com.eagle.tokens.terminals.TerminalPictureToken;

	public class Natural_DataType : TerminalPictureToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();

			// (D)
			char ch1 = rec.charAt(_currentChar);
			if (ch1 == 'D')
			{
				_endChar = _currentChar;
				foundIt(_currentLine, _endChar);
				_pic = rec.substring(_currentChar, _endChar - _currentChar);
				return true;
			}

			// (A3) (N2) (N.3)
			if (_currentChar + 1 >= recLen)
			{
				return false;
			}
			char ch2 = rec.charAt(_currentChar + 1);
			if ((ch1 == 'A' || ch1 == 'N') && (char.IsDigit(ch2) || ch2 == '.'))
			{
				_endChar = _currentChar + 1;
				while (++_endChar < recLen)
				{
					char ch = rec.charAt(_endChar);
					if (!char.IsDigit(ch) && ch != '.')
					{
						break;
					}
				}
				foundIt(_currentLine, _endChar - 1);
				_pic = rec.substring(_currentChar, (_endChar - 1) - _currentChar);
				return true;
			}

			// (P9/2)
			if ((ch1 == 'P') && char.IsDigit(ch2))
			{
				_endChar = _currentChar + 1;
				while (++_endChar < recLen)
				{
					char ch = rec.charAt(_endChar);
					if (!char.IsDigit(ch) && ch != '/')
					{
						break;
					}
				}
				foundIt(_currentLine, _endChar - 1);
				_pic = rec.substring(_currentChar, (_endChar - 1) - _currentChar);
				return true;
			}

			return false;
		}

		public override string description()
		{
			return "Natural data type, such as A21 or N2.";
		}
	}

}
