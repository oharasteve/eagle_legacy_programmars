// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

namespace com.eagle.programmar.Gupta.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Gupta_CommentToEndOfLine : TerminalCommentToken
	{
		public Gupta_CommentToEndOfLine() : this("")
		{
		}

		public Gupta_CommentToEndOfLine(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			char ch = rec.charAt(_currentChar);
			if (ch != ':')
			{
				return false;
			}
			int nc = rec.length();
			foundIt(_currentLine, nc);
			_comment = rec.substring(_currentChar, nc - _currentChar);
			return true;
		}

		public override string description()
		{
			return ": comment to end of line";
		}
	}

}
