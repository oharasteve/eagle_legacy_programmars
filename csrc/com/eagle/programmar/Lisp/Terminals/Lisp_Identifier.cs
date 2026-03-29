// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

namespace com.eagle.programmar.Lisp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalIdentifierToken = com.eagle.tokens.terminals.TerminalIdentifierToken;

	public abstract class Lisp_Identifier : TerminalIdentifierToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();
			if (_currentChar + 1 < nc)
			{
				if (rec.charAt(_currentChar) == '|')
				{
					int secondBar = rec.indexOf('|', _currentChar + 1);
					if (secondBar > _currentChar + 1)
					{
						_id = rec.substring(_currentChar, (secondBar + 1) - _currentChar);
						foundIt(_currentLine, secondBar);
						return true;
					}
				}
			}

			return genericIdentifier(lines, ALPHAS + "+-*/_", ALPHAS + DIGITS + "+-<>*/=.!$_", false, false);
		}
	}

}
