// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Text;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2015

namespace com.eagle.programmar.CMacro.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class CMacro_Definition : TerminalLiteralToken
	{
		public CMacro_Definition() : base(null, true, '\\', false, false)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			_currentLine = lines.getCurrentLine();
			_currentChar = lines.getCurrentChar();

			// Need to see if it is continued (with a backslash)
			StringBuilder macroValue = new StringBuilder();
			int lastLine = _currentLine;
			int firstChar = _currentChar;
			bool continued = true;
			int maxLine = lines.numberLines();

			// Skip the space after the macro name
			EagleLineReader rec = lines.get(lastLine);
			int recLen = rec.length();
			if (_currentChar + 1 < recLen)
			{
				_currentChar++;
			}

			while (continued && lastLine < maxLine)
			{
				rec = lines.get(lastLine);
				recLen = rec.length();
				int lastNonblank = recLen - 1;
				continued = false;

				while (lastNonblank >= 0)
				{
					char ch = rec.charAt(lastNonblank);
					if (ch == '\\')
					{
						continued = true;
						break;
					}

					if (ch != ' ' && ch != '\t')
					{
						break; // We're good. Last non-blank character is not a backslash (\)
					}

					lastNonblank--;
				}
				macroValue.Append(rec.substring(firstChar, (lastNonblank + 1) - firstChar));
				if (!continued)
				{
					break;
				}

				macroValue.Append('\n');
				firstChar = 0;
				lastLine++;
			}

			foundIt(lastLine, recLen);
			_txt = macroValue.ToString();
			return true;
		}

		public override string description()
		{
			return "Macro definition";
		}
	}
}
