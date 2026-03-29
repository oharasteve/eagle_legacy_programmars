// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using AbstractComment = com.eagle.tokens.interfaces.AbstractComment;
	using TerminalCommentToken = com.eagle.tokens.terminals.TerminalCommentToken;

	public class RPGFree_Comment : TerminalCommentToken, AbstractComment
	{
		// Need a default constructor for the parser
		public RPGFree_Comment() : this("")
		{
		}

		public RPGFree_Comment(string comment, bool hasEOLN) : base(comment, hasEOLN)
		{
		}

		public RPGFree_Comment(string comment) : base(comment)
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
			if (ch != '*')
			{
				return false;
			}
			return base.possibleCommentPair2(lines, rec, "/*", "*/");
		}

		public override string description()
		{
			return "/* comment */";
		}
	}

}
