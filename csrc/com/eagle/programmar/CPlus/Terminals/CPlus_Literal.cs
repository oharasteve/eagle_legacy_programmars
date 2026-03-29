// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 19, 2022

namespace com.eagle.programmar.CPlus.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using C_Literal = com.eagle.programmar.C.Terminals.C_Literal;

	public class CPlus_Literal : C_Literal
	{
		private static readonly string[] PREFIXES = new string[] {"u8R", "u8", "u", "L", "R", "LR"};

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();

			// Pick up the prefix(es), if they are present
			char pre1 = ' ';
			char pre2 = ' ';
			char pre3 = ' ';
			if (_currentChar < nc)
			{
				pre1 = rec.charAt(_currentChar);
			}
			if (_currentChar + 1 < nc)
			{
				pre2 = rec.charAt(_currentChar + 1);
			}
			if (_currentChar + 2 < nc)
			{
				pre3 = rec.charAt(_currentChar + 2);
			}

			int prefixLen = 0;
			foreach (string prefix in PREFIXES)
			{
				int len = prefix.Length;
				if (len >= 3 && pre3 != prefix[2])
				{
					continue;
				}
				if (len >= 2 && pre2 != prefix[1])
				{
					continue;
				}
				if (len >= 1 && pre1 != prefix[0])
				{
					continue;
				}

				prefixLen = len;
				_currentChar += prefixLen;
				lines.setCurrentChar(_currentChar);
				lines.setCurrentLine(_currentLine);
				break;
			}

			bool ok = base.parse(lines);
			if (ok)
			{
				switch (prefixLen)
				{
				case 1:
					_txt = pre1 + _txt;
					break;
				case 2:
					_txt = pre1 + pre2 + _txt;
					break;
				case 3:
					_txt = pre1 + pre2 + pre3 + _txt;
					break;
				}
			}
			_currentChar -= prefixLen;
			return ok;
		}
	}

}
