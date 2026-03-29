// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

namespace com.eagle.programmar.IBMASM.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class IBMASM_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public IBMASM_Comment() : this("")
		{
		}

		public IBMASM_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			int nc = rec.length();
			if (nc < 1)
			{
				return false;
			}

			/* Is it a comment? Star in column 1 means yes */
			if (rec.charAt(0) == '*')
			{
				_comment = rec.substring(0, nc);
				foundIt(_currentLine, nc);
				return true;
			}

			return false;
		}

		public override string description()
		{
			return "* comment to end of line";
		}
	}

}
