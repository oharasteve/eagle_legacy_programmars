// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Gupta_Literal : TerminalLiteralToken
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
			_endLine = _currentLine;
			_txt = "";
			int sc = _currentChar;
			if (ch == '\'')
			{
				int endChar = sc;
				while (true)
				{
					endChar++;
					if (endChar >= recLen)
					{
						// Continued onto the next line, ick.
						_txt += rec.substring(sc + 1);
						_endLine++;
						rec = lines.get(_endLine);
						recLen = rec.length();
						sc = -1;
						endChar = sc;
						continue;
					}
					ch = rec.charAt(endChar);
					if (ch == '\'')
					{
						break;
					}
				}
				foundIt(_endLine, endChar);
				_txt += rec.substring(sc + 1, endChar - (sc + 1));
				return true;
			}
			return false;
		}

		public override string description()
		{
			return "gupta literal";
		}
	}

}
