// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

namespace com.eagle.programmar.HTML.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class HTML_Text : TerminalLiteralToken
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
			if (ch == '<')
			{
				return false;
			}

			if (ch == '{' && _currentChar + 1 < recLen)
			{
				char ch2 = rec.charAt(_currentChar + 1);
				if (ch2 == '{' || ch2 == '%')
				{
					return false;
				}
			}

			int endChar = _currentChar;
			while (true)
			{
				endChar++;
				if (endChar >= recLen)
				{
					break;
				}
				ch = rec.charAt(endChar);
				if (ch == '<')
				{
					break;
				}
				if (ch == '{' && endChar + 1 < recLen)
				{
					char ch2 = rec.charAt(endChar + 1);
					if (ch2 == '{' || ch2 == '%')
					{
						break;
					}
				}
			}
			foundIt(_endLine, endChar - 1);
			_txt += rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "html text";
		}
	}

}
