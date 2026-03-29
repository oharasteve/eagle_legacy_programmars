// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractComment = com.eagle.tokens.interfaces.AbstractComment;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class FSharp_Comment : TerminalCommentToken, AbstractComment
	{
		public FSharp_Comment() : this("")
		{
		}

		public FSharp_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) != FOUND.GOOD)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			return possibleCommentToEndOfLine(rec, "//");
		}

		public override string description()
		{
			return "// comment to end of line";
		}
	}

}
