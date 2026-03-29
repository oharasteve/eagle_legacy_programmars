// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 8, 2026

namespace com.eagle.programmar.CMacro.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CMacro_TextLine : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			// Don't allow C lines to start with a #
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (recLen < _currentChar)
			{
				return false;
			}
			if (_currentChar < recLen && rec.charAt(_currentChar) == '#')
			{
				// Check to make sure we are at the start of a line. This check may be
				// superfluous.
				// Normally, _currentChar = 0 for a macro line that starts with #
				// In that case, the loop doesn't even execute once so it fails as a text line.
				bool atStart = true;
				for (int i = 0; i < _currentChar; i++)
				{
					char ch = rec.charAt(i);
					if (ch != ' ' && ch != '\t')
					{
						atStart = false;
						break;
					}
				}
				if (atStart)
				{
					return false;
				}
			}

			foundIt(_currentLine, recLen);
			_txt = rec.substring(_currentChar, recLen - _currentChar);
			return true;
		}

		public override string description()
		{
			return "macro text line";
		}
	}

}
