// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

namespace com.eagle.programmar.Django.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Django_CommentUntilBrace : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public Django_CommentUntilBrace() : this("")
		{
		}

		public Django_CommentUntilBrace(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			return commentUntilSentinel(lines, rec, "{%");
		}

		public override string description()
		{
			return "comment {%";
		}
	}

}
