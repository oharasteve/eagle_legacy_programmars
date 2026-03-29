// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

namespace com.eagle.programmar.CMD.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class CMD_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public CMD_Comment() : this("")
		{
		}

		public CMD_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			if (_currentChar >= rec.length())
			{
				return false;
			}
			char ch = rec.charAt(_currentChar);
			if (ch == ':')
			{
				return base.possibleCommentToEndOfLine(rec, "::");
			}
			return false;
		}

		public override string description()
		{
			return ":: comment";
		}
	}

}
