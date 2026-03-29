// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 14, 2016

namespace com.eagle.programmar.Delphi.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Delphi_Include : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public Delphi_Include() : base("")
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
			if (ch == '{')
			{
				if (!base.possibleCommentPair1(lines, rec, '{', '}'))
				{
					return false;
				}
				if (_comment.StartsWith("{$I "))
				{
					return true;
				}
				return false;
			}
			return false;
		}

		public override string description()
		{
			return "{$I include }";
		}
	}

}
