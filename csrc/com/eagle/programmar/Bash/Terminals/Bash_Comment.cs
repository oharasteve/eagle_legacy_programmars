// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Bash.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class Bash_Comment : TerminalCommentToken
	{
		// Need a default constructor for the parser
		public Bash_Comment() : this("")
		{
		}

		public Bash_Comment(string comment) : base(comment)
		{
		}

		public override bool parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF)
			{
				return false;
			}
			EagleLineReader rec = lines.get(_currentLine);
			if (!base.possibleCommentToEndOfLine(rec, "#"))
			{
				return false;
			}

			// Check for shebang (#!) on line 1, columns 1-2
			if (_currentLine > 0)
			{
				return true;
			}
			if (rec.length() < 2)
			{
				return true;
			}
			if (rec.charAt(0) != '#' || rec.charAt(1) != '!')
			{
				return true;
			}

			// Dang, it is a she-bang (#!)
			return false;
		}

		public override string description()
		{
			return "# comment";
		}
	}

}
