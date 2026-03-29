// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Template_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public Template_Comment() : this("")
		{
		}

		public Template_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}

			EagleLineReader rec = lines.get(_currentLine);
			if (_currentChar + 1 < rec.length())
			{
				char ch1 = rec.charAt(_currentChar);
				if (ch1 == '/')
				{
					char ch2 = rec.charAt(_currentChar);
					if (ch2 == '/')
					{
						return base.possibleCommentToEndOfLine(rec, "//");
					}
					if (ch2 == '*')
					{
						return base.possibleCommentPair2(lines, rec, "/*", "*/");
					}
				}
			}
			return false;
		}

		public override string description()
		{
			return "/* comment */ or // comment to end of line";
		}
	}

}
