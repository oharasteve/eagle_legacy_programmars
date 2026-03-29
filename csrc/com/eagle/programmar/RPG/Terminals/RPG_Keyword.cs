// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.RPG.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalKeywordToken = com.eagle.tokens.terminals.TerminalKeywordToken;

	public class RPG_Keyword : TerminalKeywordToken
	{
		private int fixedSc, fixedEc;

		// Used by XML Reader ...
		public RPG_Keyword() : this(0, 0, "")
		{
		}

		public RPG_Keyword(int sc, int ec, string keyword) : base(keyword)
		{
			fixedSc = sc - 1;
			fixedEc = ec;
		}

		public override bool parse(EagleFileReader lines)
		{
			EagleLineReader rec = lines.get(_currentLine);
			_endChar = rec.length();
			if (_endChar < fixedSc || fixedSc < 0)
			{
				return false; // Too short
			}
			if (_endChar > fixedEc)
			{
				_endChar = fixedEc;
			}
			string piece = rec.substring(fixedSc, _endChar - fixedSc);
			if (!piece.Equals(_word, StringComparison.OrdinalIgnoreCase))
			{
				return false; // Doesn't match, too bad
			}
			foundIt(_currentLine, _endChar - 1);
			return true;
		}
	}

}
