// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 25, 2013

namespace com.eagle.programmar.RPG.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalKeywordChoice = com.eagle.tokens.terminals.TerminalKeywordChoice;

	public class RPG_KeywordChoice : TerminalKeywordChoice
	{
		private int fixedSc, fixedEc;

		// Need default constructor for reading from the XML file
		public RPG_KeywordChoice() : this(0, 0, new string[0])
		{
		}

		public RPG_KeywordChoice(int sc, int ec, params string[] words) : base(words)
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
			string piece = rec.substring(fixedSc, _endChar - fixedSc).Trim();
			for (int i = 0; i < _words.length; i++)
			{
				if (piece.Equals(_words[i], StringComparison.OrdinalIgnoreCase))
				{
					_which = i;
					foundIt(_currentLine, _endChar - 1);
					return true;
				}
			}
			return false;
		}
	}

}
