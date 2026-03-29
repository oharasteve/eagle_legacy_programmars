// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 26, 2011

namespace com.eagle.programmar.RPG.Terminals
{
	using EagleFileReader = com.eagle.parsers.EagleFileReader;
	using EagleLineReader = com.eagle.parsers.EagleLineReader;
	using TerminalLiteralToken = com.eagle.tokens.terminals.TerminalLiteralToken;

	public class RPG_Blanks : TerminalLiteralToken
	{
		private int fixedSc, fixedEc;

		// Used by XML Reader ...
		public RPG_Blanks() : this(0, 0)
		{
		}

		public RPG_Blanks(int sc, int ec)
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
				_txt = "";
				foundIt(_currentLine, _endChar - 1);
				return true; // Too short means blanks
			}
			if (_endChar > fixedEc)
			{
				_endChar = fixedEc;
			}
			_txt = rec.substring(fixedSc, _endChar - fixedSc).Trim();
			if (_txt.length() > 0)
			{
				return false; // Means something is there, other than blanks
			}
			foundIt(_currentLine, _endChar - 1);
			return true;
		}

		public override string description()
		{
			return "RPG blanks";
		}
	}

}
