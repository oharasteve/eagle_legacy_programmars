// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

namespace com.eagle.programmar.Bash.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;

	public class Bash_SheBang : Bash_Punctuation
	{
		public Bash_SheBang() : base("#!")
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);

			// Check for shebang (#!) on line 1, columns 1-2
			if (_currentLine > 0 || _currentChar > 0)
			{
				return false;
			}
			if (rec.length() < 2)
			{
				return false;
			}
			if (rec.charAt(0) != '#' || rec.charAt(1) != '!')
			{
				return false;
			}

			// Yep, it is a she-bang
			foundIt(_currentLine, _currentChar + 1);
			return true;
		}

	}

}
