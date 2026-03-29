// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

namespace com.eagle.programmar.Lisp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class Lisp_Character : TerminalLiteralToken
	{
		private static readonly string[] specials = new string[] {"space"};

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);

			int recLen = rec.length();
			if (_currentChar + 2 >= recLen)
			{
				return false;
			}
			if (rec.charAt(_currentChar) != '#')
			{
				return false;
			}
			if (rec.charAt(_currentChar + 1) != '\\')
			{
				return false;
			}

			// Look for #\space
			foreach (string special in specials)
			{
				if (rec.charAt(_currentChar + 2) == special[0])
				{
					string piece = rec.substring(_currentChar + 2);
					if (piece.StartsWith(special, StringComparison.Ordinal))
					{
						foundIt(_currentLine, _currentChar + special.Length - 1);
						return true;
					}
				}
			}

			foundIt(_currentLine, _currentChar + 2);
			return true;
		}

		public override string description()
		{
			return "character literal";
		}
	}

}
