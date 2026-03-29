// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

namespace com.eagle.programmar.HTML.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class HTML_Pre : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public HTML_Pre() : this("")
		{
		}

		public HTML_Pre(string text) : base(text)
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
			if (_currentChar + 4 >= nc)
			{
				return false; // Need room for a fifth character
			}

			if (rec.charAt(_currentChar) != '<')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 1] != 'p')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 2] != 'r')
			{
				return false;
			}
			if (recLowerCase[_currentChar + 3] != 'e')
			{
				return false;
			}
			char nxt = recLowerCase[_currentChar + 4];
			if (nxt != '>' && nxt != ' ')
			{
				return false;
			}

			// Is the end on the same line?
			int ec = recLowerCase.IndexOf("</pre>", _currentChar + 4, StringComparison.Ordinal);
			if (ec >= 0)
			{
				// Yes! Whew!
				foundIt(_currentLine, ec + 2);
				_comment = rec.substring(_currentChar + 3, ec - (_currentChar + 3)).Trim();
				return true;
			}

			// Oh dang ... multi-line comment
			_comment = rec.substring(_currentChar + 4).Trim() + "\n";
			int lastLine = _currentLine + 1;
			int numberLines = lines.numberLines();
			while (lastLine < numberLines)
			{
				string next = lines.get(lastLine).ToString();
				string nextLowerCase = next.ToLower();
				ec = nextLowerCase.IndexOf("</pre>", StringComparison.Ordinal);
				if (ec >= 0)
				{
					foundIt(lastLine, ec + 5);
					_comment += next.Substring(0, ec).Trim();
					return true;
				}
				_comment += next.Trim() + "\n";
				lastLine++;
			}
			throw new Exception("End of HTML <pre>: missing </pre>");
		}

		public override string showString()
		{
			return "pre";
		}

		public override string description()
		{
			return "Preformmated string";
		}
	}

}
