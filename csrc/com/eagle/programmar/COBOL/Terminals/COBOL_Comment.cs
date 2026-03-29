// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

namespace com.eagle.programmar.COBOL.Terminals
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class COBOL_Comment : TerminalCommentToken
	{
		public COBOL_Comment() : this("")
		{
		}

		public COBOL_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleSyntax syntax = getSyntax();
			EagleLineReader rec = lines.get(_currentLine);
			if (_currentChar != syntax._commentColumn)
			{
				if (_currentChar != syntax._commentColumn + 1)
				{
					return false; // The '*' must be in column 1 for free format, 7 or 8 for fixed
				}
			}
			char ch = rec.charAt(_currentChar);
			if (ch != '*' && ch != '/')
			{
				return false;
			}

			_endChar = syntax.recLen(lines, _currentLine);
			foundIt(_currentLine, _endChar);
			_comment = rec.substring(_currentChar, _endChar - _currentChar);
			return true;
		}

		public override string description()
		{
			return "* comment";
		}
	}

}
