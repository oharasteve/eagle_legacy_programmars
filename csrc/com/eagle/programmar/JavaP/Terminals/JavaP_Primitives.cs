// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 25, 2015

namespace com.eagle.programmar.JavaP.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class JavaP_Primitives : TerminalLiteralToken
	{
		private const string PRIMITIVES = "BCDIJVZ";

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
			if (PRIMITIVES.IndexOf(ch) < 0)
			{
				return false;
			}

			int endChar = _currentChar + 1;
			while (endChar < recLen)
			{
				ch = rec.charAt(endChar);
				if (PRIMITIVES.IndexOf(ch) < 0)
				{
					if (char.IsLetterOrDigit(ch))
					{
						return false;
					}
					endChar--;
					break;
				}
				endChar++;
			}

			foundIt(_currentLine, endChar);
			_txt = rec.substring(_currentChar, endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "primitive";
		}
	}

}
