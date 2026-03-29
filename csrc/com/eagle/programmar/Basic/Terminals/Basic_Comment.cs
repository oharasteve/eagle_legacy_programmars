// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Basic_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public Basic_Comment() : this("")
		{
		}

		public Basic_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			// Grab the rest of the line
			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();
			foundIt(_currentLine, nc);
			_comment = rec.substring(_currentChar, nc - _currentChar);
			return true;
		}

		public override string description()
		{
			return "REM comment to end of line";
		}
	}

}
