// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2010

namespace com.eagle.programmar.CSharp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractComment = com.eagle.tokens.interfaces.AbstractComment;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class CSharp_Comment : TerminalCommentToken, AbstractComment
	{
		// Need a default constructor for the parser
		public CSharp_Comment() : this("")
		{
		}

		public CSharp_Comment(string comment, bool hasEOLN) : base(comment, hasEOLN)
		{
		}

		public CSharp_Comment(string comment) : base(comment)
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
			if (_currentChar + 1 >= nc)
			{
				return false;
			}
			char ch = rec.charAt(_currentChar);
			if (ch == '/')
			{
				char ch2 = rec.charAt(_currentChar + 1);
				switch (ch2)
				{
				case '/':
					return base.possibleCommentToEndOfLine(rec, "//");
				case '*':
					return base.possibleCommentPair2(lines, rec, "/*", "*/");
				}
			}
			else if (ch == '#')
			{
				if (rec.contains("#region") || rec.contains("#endregion"))
				{
					foundIt(_currentLine, nc);
					_comment = rec.substring(_currentChar, nc - _currentChar);
					return true;
				}
			}

			return false;
		}

		public override string description()
		{
			return "#region or /* comment */ or // comment to end of line";
		}
	}

}
