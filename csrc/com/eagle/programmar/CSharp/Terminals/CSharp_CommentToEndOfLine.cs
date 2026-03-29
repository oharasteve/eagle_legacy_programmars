// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

namespace com.eagle.programmar.CSharp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class CSharp_CommentToEndOfLine : TerminalCommentToken
	{
		public CSharp_CommentToEndOfLine() : this("")
		{
		}

		public CSharp_CommentToEndOfLine(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			_endChar = rec.length();
			foundIt(_currentLine, _endChar);
			_comment = rec.substring(_currentChar, _endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "comment to end of line";
		}
	}

}
