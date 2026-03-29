// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 21, 2026

namespace com.eagle.programmar.HTML.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class HTML_Code : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public HTML_Code() : this("")
		{
		}

		public HTML_Code(string text) : base(text)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			string recLowerCase = rec.toLowerCase();
			int nc = rec.length();
			if (_currentChar + 5 >= nc)
			{
				return false; // Need room for a sixth character
			}

			if (rec.charAt(_currentChar) != '<')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 1] != 'c')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 2] != 'o')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 3] != 'd')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 4] != 'e')
			{
				return false;
			}
			char nxt = recLowerCase[_currentChar + 5];
			if (nxt != '>' && nxt != ' ')
			{
				return false;
			}

			// Is the end on the same line?
			int ec = recLowerCase.IndexOf("</code>", _currentChar + 5, StringComparison.Ordinal);
			if (ec >= 0)
			{
				// Yes! Whew!
				foundIt(_currentLine, ec + 3);
				_comment = rec.substring(_currentChar + 4, ec - (_currentChar + 4)).Trim();
				return true;
			}

			// Oh dang ... multi-line comment
			_comment = rec.substring(_currentChar + 5).Trim() + "\n";
			int lastLine = _currentLine + 1;
			int numberLines = lines.numberLines();
			while (lastLine < numberLines)
			{
				string next = lines.get(lastLine).ToString();
				string nextLowerCase = next.ToLower();
				ec = nextLowerCase.IndexOf("</code>", StringComparison.Ordinal);
				if (ec >= 0)
				{
					foundIt(lastLine, ec + 6);
					_comment += next.Substring(0, ec).Trim();
					return true;
				}
				_comment += next.Trim() + "\n";
				lastLine++;
			}
			throw new Exception("End of HTML <code>: missing </code>");
		}

		public override string showString()
		{
			return "code";
		}

		public override string description()
		{
			return "Preformmated code";
		}
	}

}
