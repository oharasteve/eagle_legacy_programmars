// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2011

namespace com.eagle.programmar.CSS.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CSS_FileName : TerminalLiteralToken
	{
		private static string ALLOWED = ":-/_.#?";

		public CSS_FileName() : base("\"'", true, '\\', false, false)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar >= recLen)
			{
				return false;
			}
			char ch = rec.charAt(_currentChar);
			if (ch == '\'' || ch == '"')
			{
				return base.parse(lines);
			}

			// Accept anything that looks like a filename
			int endChar = _currentChar;
			while (endChar < recLen)
			{
				ch = rec.charAt(endChar);
				if (ALLOWED.IndexOf(ch) < 0 && !char.IsLetterOrDigit(ch))
				{
					break;
				}
				endChar++;
			}
			_txt = rec.substring(_currentChar, endChar - _currentChar);
			foundIt(_currentLine, endChar - 1);
			return true;
		}

		public override string description()
		{
			return "file name";
		}
	}

}
