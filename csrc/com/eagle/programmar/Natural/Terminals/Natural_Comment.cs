// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

namespace com.eagle.programmar.Natural.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Natural_Comment : TerminalCommentToken
	{
		public Natural_Comment() : this("")
		{
		}

		public Natural_Comment(string comment) : base(comment)
		{
		}

		// Allowed patterns are star-eoln, star-space, star-star, slash-star
		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(this._currentLine);
			int nc = rec.length();

			char firstChar = rec.charAt(_currentChar);
			if (firstChar == '*' || firstChar == '/')
			{
				char nextChar;
				if (_currentChar + 1 >= nc)
				{
					nextChar = ' ';
					_comment = "";
				}
				else
				{
					nextChar = rec.charAt(_currentChar + 1);
					_comment = rec.substring(_currentChar, nc - _currentChar);
				}

				if ((firstChar == '*' && nextChar == ' ') || nextChar == '*')
				{
					foundIt(_currentLine, nc);
					return true;
				}
			}
			return false;
		}

		public override string description()
		{
			return "* or ** or /* comment";
		}
	}

}
