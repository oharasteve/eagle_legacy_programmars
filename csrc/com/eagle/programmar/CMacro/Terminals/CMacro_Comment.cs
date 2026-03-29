// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2020

namespace com.eagle.programmar.CMacro.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class CMacro_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public CMacro_Comment() : this("")
		{
		}

		public CMacro_Comment(string comment) : base(comment)
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
			if (_currentChar + 1 >= nc)
			{
				return false;
			}
			if (rec.charAt(_currentChar) != '/')
			{
				return false;
			}

			char ch = rec.charAt(_currentChar + 1);
			switch (ch)
			{
			case '/':
				return base.possibleCommentToEndOfLine(rec, "//");
			case '*':
				return base.possibleCommentPair2(lines, rec, "/*", "*/");
			}
			return false;
		}

		public override string description()
		{
			return "/* comment */ or // comment to end of line";
		}
	}

}
