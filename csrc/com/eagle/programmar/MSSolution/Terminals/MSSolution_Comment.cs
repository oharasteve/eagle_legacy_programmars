// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentRestOfLineToken = com.eagle.tokens.terminals.TerminalCommentRestOfLineToken;

	public class MSSolution_Comment : TerminalCommentRestOfLineToken
	{
		// Need a default constructor for the parser
		public MSSolution_Comment() : this("")
		{
		}

		public MSSolution_Comment(string comment) : base(comment)
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
	}

}
