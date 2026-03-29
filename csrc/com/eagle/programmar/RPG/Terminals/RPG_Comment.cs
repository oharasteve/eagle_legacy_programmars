// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class RPG_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public RPG_Comment() : this("")
		{
		}

		public RPG_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();
			if (6 >= nc)
			{
				return false; // Need room for 7 columns
			}

			/* Is it a comment? Star in column 7 means yes */
			if (rec.charAt(6) == '*')
			{
				_comment = rec.substring(6, nc - 6);
				foundIt(_currentLine, nc);
				return true;
			}
			return false;
		}

		public override string description()
		{
			return "* comment";
		}
	}

}
