// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

namespace com.eagle.programmar.PLI.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalPictureToken = com.eagle.tokens.terminals.TerminalPictureToken;

	public class PLI_Picture : TerminalPictureToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			char ch1 = rec.charAt(_currentChar);
			if (ch1 != '\'')
			{
				return false;
			}

			_endChar = _currentChar + 1;
			while (_endChar < recLen)
			{
				char ch2 = rec.charAt(_endChar);
				if (ch2 == '\'')
				{
					break;
				}
				_endChar++;
			}

			foundIt(_currentLine, _endChar);
			_pic = rec.substring(_currentChar, (_endChar + 1) - _currentChar);
			return true;
		}

		public override string description()
		{
			return "A PL/I PICTURE, such '99V99' or 'X(10)'";
		}
	}

}
