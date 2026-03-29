// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

namespace com.eagle.programmar.Python.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractComment = com.eagle.tokens.interfaces.AbstractComment;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Python_Comment : TerminalCommentToken, AbstractComment
	{
		public Python_Comment() : this("")
		{
		}

		public Python_Comment(string comment, bool hasEOLN) : base(comment, hasEOLN)
		{
		}

		public Python_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) != FOUND.GOOD)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();
			if (_currentChar < nc)
			{
				char ch = rec.charAt(_currentChar);

				if (ch == '#')
				{
					foundIt(_currentLine, nc);
					_comment = rec.substring(_currentChar, nc - _currentChar);
					return true;
				}

				if (ch == '/')
				{
					return base.possibleCommentPair2(lines, rec, "/*", "*/");
				}
			}

			return false;
		}

		public override string description()
		{
			return "/* comment */ or # comment to end of line";
		}
	}

}
