// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

namespace com.eagle.programmar.Eaglish.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractComment = com.eagle.tokens.interfaces.AbstractComment;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Eaglish_Comment : TerminalCommentToken, AbstractComment
	{
		public Eaglish_Comment() : this("")
		{
		}

		public Eaglish_Comment(string comment, bool hasEOLN) : base(comment, hasEOLN)
		{
		}

		public Eaglish_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();
			if (_currentChar >= nc)
			{
				return false;
			}
			if (rec.charAt(_currentChar) != '#')
			{
				return false;
			}

			foundIt(_currentLine, nc);
			_comment = rec.substring(_currentChar, nc - _currentChar);
			return true;
		}

		public override string description()
		{
			return "# comment to end of line";
		}
	}

}
