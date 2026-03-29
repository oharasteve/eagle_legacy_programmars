// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 17, 2020

namespace com.eagle.programmar.BNF.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class BNF_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public BNF_Comment() : this("")
		{
		}

		public BNF_Comment(string comment) : base(comment)
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
			if (ch == '(')
			{
				return base.possibleCommentPair2(lines, rec, "(*", "*)");
			}
			return false;
		}

		public override string description()
		{
			return "(* comment *)";
		}
	}

}
