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

	public class CMacro_MultiLineText : TerminalLiteralToken
	{
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) != FOUND.GOOD)
			{
				return false;
			}
			StringBuilder text = new StringBuilder();
			int endLine = _currentLine;
			EagleLineReader firstRec = lines.get(_currentLine);
			int recLen = firstRec.length();
			if (recLen < _currentChar)
			{
				return false;
			}

			bool inComment = false;
			while (endLine < lines.numberLines())
			{
				EagleLineReader rec = lines.get(endLine);
				recLen = rec.length();
				endLine++;

				// Don't allow lines that start with a #
				if (!inComment && rec.trim().StartsWith("#"))
				{
					endLine--;
					if (endLine == _currentLine)
					{
						return false;
					}
					break;
				}

				bool inQuotes = false;
				for (int i = 0; i < recLen - 1; i++) // -1 so we don't run off the end
				{
					char ch = rec.charAt(i);
					char nextch = rec.charAt(i + 1);
					if (ch == '"')
					{
						inQuotes = !inQuotes;
					}
					if (!inQuotes)
					{
						if (ch == '/' && nextch == '*')
						{
							inComment = true;
						}
						if (ch == '*' && nextch == '/')
						{
							inComment = false;
						}
						if (ch == '/' && nextch == '/')
						{
							break;
						}
					}
				}

				if (text.Length > 0)
				{
					text.Append('\n');
				}
				text.Append(rec);
			}
			if (text.Length == 0)
			{
				return false;
			}

			_txt = text.ToString();
			foundIt(endLine, -1);
			return true;
		}

		public override string description()
		{
			return "multiline text";
		}
	}
}
