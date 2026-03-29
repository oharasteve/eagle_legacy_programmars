// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

namespace com.eagle.programmar.HTML.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class HTML_CData : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar + 8 >= recLen)
			{
				return false;
			}

			if (rec.charAt(_currentChar) == '<' && rec.charAt(_currentChar + 1) == '!' && rec.charAt(_currentChar + 2) == '[' && rec.charAt(_currentChar + 3) == 'C' && rec.charAt(_currentChar + 4) == 'D' && rec.charAt(_currentChar + 5) == 'A' && rec.charAt(_currentChar + 6) == 'T' && rec.charAt(_currentChar + 7) == 'A' && rec.charAt(_currentChar + 8) == '[')
			{
				_endLine = _currentLine;
				_txt = "";
				int endChar = _currentChar + 8;
				int sc = _currentChar;
				while (true)
				{
					endChar++;
					if (endChar + 2 >= recLen)
					{
						// Continued onto the next line, ick.
						_txt += rec.substring(sc < 0 ? 0 : sc) + '\n';
						_endLine++;
						rec = lines.get(_endLine);
						recLen = rec.length();
						sc = -1;
						endChar = sc;
						continue;
					}

					if (rec.charAt(endChar) == ']' && rec.charAt(endChar + 1) == ']' && rec.charAt(endChar + 2) == '>')
					{
						foundIt(_endLine, endChar + 2);
						_txt += rec.substring(sc < 0 ? 0 : sc, (endChar + 3) - (sc < 0 ? 0 : sc));
						return true;
					}
				}
			}
			return false;
		}

		public override string description()
		{
			return "html cdata";
		}
	}

}
